package project.local.controller.card;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.local.LocalCardBenefitsDTO;

import project.local.service.CardServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/woorieodi/card")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class CardController {

    private final CardServiceImpl cardService;

    // 혜택 클릭시 서칭해야하니까 먼저 전체 데이터를 불러오는거임
    @GetMapping
    public List<LocalCardBenefitsDTO> findAllCardBenefit() {
        List<LocalCardBenefitsDTO> cardBenefits = cardService.findAllCardBenefit();
        return cardBenefits;

    }

}
