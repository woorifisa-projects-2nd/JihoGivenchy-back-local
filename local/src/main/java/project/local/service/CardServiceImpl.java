package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import project.local.dto.cardDetails.CardDetailDTO;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.dto.local.LocalCardDTO;
import project.local.dto.local.SearchDTO;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {

    private final CardBenefitsRepository cardBenefitsRepository;
    private final CardRepository cardRepository;

    // 혜택 데이터 전부 불러오기
    public List<LocalCardBenefitsDTO> findAllCardBenefit() {
        List<CardBenefits> cardBenefits = cardBenefitsRepository.findAll();

        List<LocalCardBenefitsDTO> localCardBenefitsDTOS = new ArrayList<>();

        for (CardBenefits benefit : cardBenefits) {
            LocalCardBenefitsDTO localCardBenefitsDTO = LocalCardBenefitsDTO.builder()
                    .id(benefit.getId())
                    .category(benefit.getCategory())
                    .cardType(benefit.getCard().getCardType())
                    .cardId(benefit.getCard().getId()).build();

            localCardBenefitsDTOS.add(localCardBenefitsDTO);
        }

        return localCardBenefitsDTOS;
    }

    public List<LocalCardDTO> findCardDetails(SearchDTO searchDTO) {
        List<Long> cardIds = searchDTO.getCardId();
        List<LocalCardDTO> cards = new ArrayList<>();

        for (Long cardId : cardIds) {
            Card card = cardRepository.findById(cardId).orElseThrow();
            List<CardBenefits> byCardId = cardBenefitsRepository.findByCard_Id(cardId);
            List<CardDetailDTO> detailDTOs = new ArrayList<>();

            for (CardBenefits cardBenefits : byCardId) {
                detailDTOs.add(CardDetailDTO.builder()
                        .benefitTitle(cardBenefits.getBenefitTitle())
                        .benefitSummary(cardBenefits.getBenefitSummary())
                        .build());
            }
            cards.add(LocalCardDTO.builder()
                    .cardImage(card.getCardImage())
                    .cardName(card.getCardName())
                    .cardCompany(card.getCardCompany())
                    .annualFee(card.getAnnualFee())
                    .previousAmount(card.getPreviousAmount())
                    .benefits(detailDTOs)
                    .build());
        }
        return cards;
    }
}
