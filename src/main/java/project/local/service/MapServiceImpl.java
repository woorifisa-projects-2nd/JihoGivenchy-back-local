package project.local.service;

import org.springframework.stereotype.Service;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.repository.CardBenefitsRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl {

    private final CardBenefitsRepository cardBenefitsRepository;

    public MapServiceImpl(CardBenefitsRepository cardBenefitsRepository) {
        this.cardBenefitsRepository = cardBenefitsRepository;
    }

    public List<LocalCardBenefitsDTO> findCardBenefitsByCategory(String category) {
        return cardBenefitsRepository.findByCategory(category)
                .stream()
                        .map(cardBenefit -> LocalCardBenefitsDTO.builder()
                        .id(cardBenefit.getId()) // id 추가
                        .cardId(cardBenefit.getCard().getId())
                        .cardType(cardBenefit.getBenefitSummary())
                        .benefits(Collections.singletonList(cardBenefit.getBenefitTitle())) // List로 변환
                        .category(cardBenefit.getCategory())
                        .build())
                .collect(Collectors.toList());
    }
}

