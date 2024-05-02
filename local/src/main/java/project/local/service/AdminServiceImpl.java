package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.local.dto.cardDetails.CardDetailDTO;
import project.local.dto.local.LocalCardDTO;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl {

    private final CardRepository cardRepository;
    private final CardBenefitsRepository cardBenefitsRepository;

    public List<LocalCardDTO> findCards() {
        List<Card> all = cardRepository.findAll();
        List<LocalCardDTO> localCardDTOs = new ArrayList<>();
        for (Card card : all) {
            // LocalCardDTO 객체를 생성하고 benefits를 설정
            LocalCardDTO localCardDTO = LocalCardDTO.builder()
                    .cardImage(card.getCardImage())
                    .id(card.getId())
                    .cardCompany(card.getCardCompany())
                    .cardName(card.getCardName())
                    .cardType(card.getCardType())
                    .build();
            localCardDTOs.add(localCardDTO);
        }
        return localCardDTOs;
    }

    @Transactional
    public void saveCard(LocalCardDTO localCardDTO) {
        Card card = Card.builder()
                .cardImage(localCardDTO.getCardImage())
                .cardType(localCardDTO.getCardType())
                .cardName(localCardDTO.getCardName())
                .cardCompany(localCardDTO.getCardCompany())
                .annualFee(localCardDTO.getAnnualFee())
                .previousAmount(localCardDTO.getPreviousAmount())
                .build();
        Card save = cardRepository.save(card);

        List<CardDetailDTO> benefits = localCardDTO.getBenefits();
        for (CardDetailDTO benefit : benefits) {
            CardBenefits cardBenefits = CardBenefits.builder()
                    .category(benefit.getCategory())
                    .benefitTitle(benefit.getBenefitTitle())
                    .benefitSummary(benefit.getBenefitSummary())
                    .card(save)
                    .build();
            cardBenefitsRepository.save(cardBenefits);
        }
    }

    public LocalCardDTO findForUpdate(Long id) {
        Card card = cardRepository.findById(id).orElse(null);
        List<CardBenefits> cardBenefitsList = cardBenefitsRepository.findByCard_Id(id);
        List<CardDetailDTO> cardDetailDTOs = new ArrayList<>();
        for (CardBenefits cardBenefits : cardBenefitsList) {
            if (cardBenefits.getBenefitTitle() != null && !cardBenefits.getBenefitTitle().isEmpty()
                    && cardBenefits.getBenefitSummary() != null && !cardBenefits.getBenefitSummary().isEmpty()) {
                cardDetailDTOs.add(CardDetailDTO.builder()
                        .benefitsId(cardBenefits.getId())
                        .category(cardBenefits.getCategory())
                        .benefitTitle(cardBenefits.getBenefitTitle())
                        .benefitSummary(cardBenefits.getBenefitSummary())
                        .build());
            }
        }

        return LocalCardDTO.builder()
                .id(id)
                .cardImage(card.getCardImage())
                .cardCompany(card.getCardCompany())
                .cardType(card.getCardType())
                .cardName(card.getCardName())
                .annualFee(card.getAnnualFee())
                .previousAmount(card.getPreviousAmount())
                .benefits(cardDetailDTOs)
                .build();
    }

    @Transactional
    public void updateCard(LocalCardDTO localCardDTO) {
        cardRepository.findById(localCardDTO.getId()).ifPresent(card -> {
            Optional.ofNullable(localCardDTO.getCardImage()).ifPresent(card::setCardImage);
            Optional.ofNullable(localCardDTO.getCardCompany()).ifPresent(card::setCardCompany);
            Optional.ofNullable(localCardDTO.getCardName()).ifPresent(card::setCardName);
            Optional.ofNullable(localCardDTO.getAnnualFee()).ifPresent(card::setAnnualFee);
            Optional.ofNullable(localCardDTO.getPreviousAmount()).ifPresent(card::setPreviousAmount);
            Optional.ofNullable(localCardDTO.getCardType()).ifPresent(card::setCardType);
        });
    }

    @Transactional
    public void updateBenefits(LocalCardDTO localCardDTO) {
        List<CardBenefits> benefitsList = cardBenefitsRepository.findByCard_Id(localCardDTO.getId());
        benefitsList.forEach(cardBenefits ->
                localCardDTO.getBenefits().stream()
                        .filter(benefit -> benefit.getBenefitsId().equals(cardBenefits.getId()))
                        .findFirst()
                        .ifPresent(benefit -> {
                            cardBenefits.setBenefitTitle(benefit.getBenefitTitle());
                            cardBenefits.setBenefitSummary(benefit.getBenefitSummary());
                        })
        );
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}