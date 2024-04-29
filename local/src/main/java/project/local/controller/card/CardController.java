package project.local.controller.card;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.local.dto.cardDetails.CardDetailDTO;
import project.local.dto.cardDetails.CardDetailRequestDTO;
import project.local.dto.local.LocalCardBenefitsDTO;

import project.local.dto.local.LocalCardDTO;
import project.local.dto.local.SearchDTO;
import project.local.service.CardServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/universe/card")
@RequiredArgsConstructor
public class CardController {

    private final CardServiceImpl cardService;

    // 혜택 클릭시 서칭해야하니까 먼저 전체 데이터를 불러오는거임
    @GetMapping
    public List<LocalCardBenefitsDTO> findAllCardBenefit() {
        return cardService.findAllCardBenefit();

    }

    @PostMapping
    public List<LocalCardDTO> searchCards(@RequestBody SearchDTO searchDTO) {
        System.out.println("searchDTO = " + searchDTO);
         return cardService.findCardDetails(searchDTO);
    }
}
