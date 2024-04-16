package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.entity.cardInfo.CardBenefits;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {

    private final CardBenefitsRepository cardBenefitsRepository;

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
}
