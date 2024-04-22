package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.entity.Category;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapServiceImpl {

    private final MyDataServiceImpl myDataService;
    private final CardBenefitsRepository cardBenefitsRepository;
    private final CardRepository cardRepository;

    public String getCategoryByCode(String code) {
        for (Category category : Category.values()) {
            if (category.name().equals(code)) {
                return category.getCategory();
            }
        }
        return null; // 일치하는 코드가 없는 경우 null 반환
    }

    public LocalCardBenefitsDTO findCardByCategory(String category, Long id) throws Exception {
        List<CardsDTO> cardsDTOS = myDataService.requestCards(id);

        for (CardsDTO cardsDTO : cardsDTOS) {
            CardBenefits byCardIdAndCategoryContaining = cardBenefitsRepository.findByCard_IdAndCategoryMapContaining(cardsDTO.getCardId(), category);
            if (byCardIdAndCategoryContaining != null) {
                Card card = cardRepository.findById(cardsDTO.getCardId()).orElse(null);
                return LocalCardBenefitsDTO.builder()
                        .image(card.getCardImage())
                        .cardName(card.getCardName())
                        .benefitTitleMap(byCardIdAndCategoryContaining.getBenefitTitleMap())
                        .benefitSummaryMap(byCardIdAndCategoryContaining.getBenefitSummaryMap())
                        .build();
            }
        }
        return null;
    }
}