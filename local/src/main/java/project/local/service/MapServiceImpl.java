package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.dto.map.StoreDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.entity.Category;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.entity.storeInfo.Store;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;
import project.local.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapServiceImpl {

    private final MyDataServiceImpl myDataService;
    private final CardBenefitsRepository cardBenefitsRepository;
    private final CardRepository cardRepository;
    private final StoreRepository storeRepository;

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

    public List<StoreDTO> findAllStore() {
        List<Store> storeList = storeRepository.findAll();

        List<StoreDTO> storeDTOS = new ArrayList<>();

        for (Store store : storeList) {
            StoreDTO storeDTO  = StoreDTO.builder()
                    .storeId(store.getStoreId())
                    .storeName(store.getStoreName())
                    .storeAddress(store.getStoreAddress())
                    .storeCategory(store.getStoreCategory())
                    .storeRegDate(store.getStoreRegDate())
                    .storeExDate(store.getStoreExDate())
                    .build();

            storeDTOS.add(storeDTO);
        }
        return storeDTOS;
    }
}