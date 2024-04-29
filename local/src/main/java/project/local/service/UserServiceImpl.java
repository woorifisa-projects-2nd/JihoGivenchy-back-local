package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.cardDetails.CardDetailDTO;
import project.local.dto.local.LocalCardDTO;
import project.local.dto.mydata.BillsDTO;
import project.local.dto.mydata.BillsDetailsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.mypage.SpentAmountDTO;
import project.local.dto.mypage.TimeAndTotalAmountDTO;
import project.local.entity.Category;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.entity.userInfo.User;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;
import project.local.repository.UserRepository;
import project.local.service.inter.UserService;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final CardBenefitsRepository cardBenefitsRepository;

    // 회원 찾고 그 회원의 보유 카드 찾기 // null이면 회원이 아님.
    @Override
    public Long findUser(Long id) {
        User userInfo = userRepository.findById(id).orElse(null);
        Long userId = userInfo.getId();

        return userId;
    }

    @Override
    public List<LocalCardDTO> findMyCardLists(List<CardsDTO> cards) {
        List<LocalCardDTO> myCards = new ArrayList<>();
        for (CardsDTO card : cards) {
            Card byId = cardRepository.findById(card.getCardId()).orElseThrow();
            List<CardBenefits> byCardIds = cardBenefitsRepository.findByCard_Id(byId.getId());
            List<CardDetailDTO> cardDetailDTOs = new ArrayList<>();
            System.out.println("byCardIds.size() = " + byCardIds.size());
            for (CardBenefits byCardId : byCardIds) {
                cardDetailDTOs.add(CardDetailDTO.builder()
                        .benefitTitle(byCardId.getBenefitTitle())
                        .benefitSummary(byCardId.getBenefitSummary())
                        .build());
            }
            myCards.add(LocalCardDTO.builder()
                            .cardImage(byId.getCardImage())
                            .cardType(byId.getCardType())
                            .cardName(byId.getCardName())
                            .cardCompany(byId.getCardCompany())
                            .annualFee(byId.getAnnualFee())
                            .previousAmount(byId.getPreviousAmount())
                            .benefits(cardDetailDTOs)
                    .build());
        }
        return myCards;
    }

    @Override
    public TimeAndTotalAmountDTO getTimeAndTotalAmount(List<BillsDTO> bills, LocalDate time) {
        String year = String.valueOf(time.getYear());
        int month = time.getMonthValue() - 1;
        String realMonth = null;

        if (month == 0) {
            realMonth = String.valueOf(12);
        } else if (month / 10 == 0) {
            realMonth = "0" + month;
        }

        String str = year + realMonth;
        TimeAndTotalAmountDTO timesAndtotalAmount = null;

        for (BillsDTO bill : bills) {
            if (String.valueOf(bill.getChargeMonth()).equals(str)) {
                timesAndtotalAmount = new TimeAndTotalAmountDTO(bill.getChargeMonth(), bill.getChargeAmt());
            }
        }
        return timesAndtotalAmount;
    }

    @Override
    public SpentAmountDTO findSpentAmount(List<BillsDetailsDTO> billsDetails) {
        Map<String, Integer> categories = new HashMap<>();
        Set<String> specifiedCategories = Set.of("음식점", "카페", "주유소", "쇼핑", "편의점", "대형마트", "영화관");

        for (BillsDetailsDTO billsDetail : billsDetails) {
            String category = billsDetail.getMerchantType();
            int paidAmount = billsDetail.getPaidAmt();

            // 각 업종별로 지출 금액을 합산
            categories.merge(category, paidAmount, Integer::sum);
        }
        int etcSum = 0;

        // 가장 큰 지출을 찾기
        String maxCategory = null;
        int maxValue = 0;

        for (Map.Entry<String, Integer> entry : categories.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxCategory = entry.getKey();
            }
            if (!specifiedCategories.contains(entry.getKey())) {
                etcSum += entry.getValue();
            }
        }

        return SpentAmountDTO.builder()
                .restaurant(categories.getOrDefault("음식점", 0))
                .cafe(categories.getOrDefault("카페", 0))
                .gasStation(categories.getOrDefault("주유", 0))
                .shopping(categories.getOrDefault("쇼핑", 0))
                .convenienceStore(categories.getOrDefault("편의점", 0))
                .supermarket(categories.getOrDefault("대형마트", 0))
                .movie(categories.getOrDefault("영화관", 0))
                .etc(etcSum)
                .maxCategoryValue(maxCategory)
                .build();
    }

    @Override
    public String getCategoryCodeFromValue(String categoryValue) {
        for (Category category : Category.values()) {
            if (category.getCategory().equals(categoryValue)) {
                return category.name();
            }
        }
        return null;
    }

}
