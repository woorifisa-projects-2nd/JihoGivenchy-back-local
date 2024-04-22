package project.local.controller.card;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.local.dto.cardDetails.CardDetailDTO;
import project.local.dto.cardDetails.CardDetailRequestDTO;
import project.local.dto.local.LocalCardBenefitsDTO;

import project.local.service.CardServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/woorieodi/card")
@RequiredArgsConstructor
public class CardController {

    private final CardServiceImpl cardService;

    // 혜택 클릭시 서칭해야하니까 먼저 전체 데이터를 불러오는거임
    @GetMapping
    public List<LocalCardBenefitsDTO> findAllCardBenefit() {
        List<LocalCardBenefitsDTO> cardBenefits = cardService.findAllCardBenefit();
        return cardBenefits;

    }

    @PostMapping("/carddetail")
    public List<CardDetailDTO> processCardDetail(@RequestBody CardDetailRequestDTO[] cardDetailRequestDTOS) {

        ArrayList<CardDetailDTO> list = new ArrayList<>();
        for (CardDetailRequestDTO x : cardDetailRequestDTOS) list.add(cardService.findById(x.getCardId()));

        return list;

    }

}
