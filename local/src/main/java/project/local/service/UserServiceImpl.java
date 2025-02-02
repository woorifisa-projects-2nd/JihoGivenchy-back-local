package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.local.dto.cardDetails.CardDetailDTO;
import project.local.dto.local.LocalCardDTO;
import project.local.dto.loginAndSingUp.UserDTO;
import project.local.dto.mydata.BillsDTO;
import project.local.dto.mydata.BillsDetailsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.mypage.HelpDTO;
import project.local.dto.mypage.SpentAmountDTO;
import project.local.dto.mypage.TimeAndTotalAmountDTO;
import project.local.entity.Category;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.entity.userInfo.Inquiry;
import project.local.entity.userInfo.User;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;
import project.local.repository.InquiryRepository;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final InquiryRepository inquiryRepository;


    // 회원 찾고 그 회원의 보유 카드 찾기 // null이면 회원이 아님.
    @Override
    public Long findUser(Long id) {
        User userInfo = userRepository.findById(id).orElse(null);
        Long userId = userInfo.getId();

        return userId;
    }


    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        return user;
    }

    @Override
    public List<LocalCardDTO> findMyCardLists(List<CardsDTO> cards) {
        List<LocalCardDTO> myCards = new ArrayList<>();
        for (CardsDTO card : cards) {
            Card byId = cardRepository.findById(card.getCardId()).orElseThrow();
            List<CardBenefits> byCardIds = cardBenefitsRepository.findByCard_Id(byId.getId());
            List<CardDetailDTO> cardDetailDTOs = new ArrayList<>();
            for (CardBenefits byCardId : byCardIds) {
                if (byCardId.getBenefitTitle() != null && !byCardId.getBenefitTitle().isEmpty() &&
                        byCardId.getBenefitSummary() != null && !byCardId.getBenefitSummary().isEmpty()) {
                    cardDetailDTOs.add(CardDetailDTO.builder()
                            .benefitTitle(byCardId.getBenefitTitle())
                            .benefitSummary(byCardId.getBenefitSummary())
                            .build());
                }
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

    @Override
    public UserDTO findForUpdate(Long id) {
        User user = userRepository.findById(id).orElse(null);

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .nickName(user.getNickName())
                .build();
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        userRepository.findById(userDTO.getId()).ifPresent(user -> {
            Optional.ofNullable(userDTO.getName()).ifPresent(user::setName);
            Optional.ofNullable(userDTO.getNickName()).ifPresent(user::setNickName);

            String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
            user.setPassword(encodedPassword);
        });
    }

    @Override
    public List<HelpDTO> findHelps(Long userId) {
        List<Inquiry> byUserId = inquiryRepository.findByUser_IdOrderByIsAnswerDesc(userId);
        List<HelpDTO> helpDTOs = new ArrayList<>();
        for (Inquiry inquiry : byUserId) {
            helpDTOs.add(HelpDTO.builder()
                    .questionId(inquiry.getQuestionId())
                    .userId(userId)
                    .userName(inquiry.getUser().getName())
                    .inquiryTitle(inquiry.getTitle())
                    .inquiryCategory(inquiry.getCategory())
                    .isAnswer(inquiry.getIsAnswer())
                    .build());
        }
        return helpDTOs;
    }

    public HelpDTO findHelp (Long questionId) {
        Inquiry inquiry = inquiryRepository.findById(questionId).orElse(null);
        return HelpDTO.builder()
                .questionId(questionId)
                .userId(inquiry.getUser().getId())
                .userName(inquiry.getUser().getName())
                .inquiryTitle(inquiry.getTitle())
                .inquiryCategory(inquiry.getCategory())
                .inquiryContent(inquiry.getContent())
                .isAnswer(inquiry.getIsAnswer())
                .answer(inquiry.getAnswer())
                .build();
    }

    @Override
    public void saveHelp(HelpDTO helpDTO, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Inquiry inquiry = Inquiry.builder()
                .category(helpDTO.getInquiryCategory())
                .title(helpDTO.getInquiryTitle())
                .content(helpDTO.getInquiryContent())
                .isAnswer(0)
                .user(user)
                .build();
        inquiryRepository.saveAndFlush(inquiry);
    }

    @Override
    public void deleteHelp(Long questionId) {
        inquiryRepository.deleteById(questionId);
    }

}
