package project.local.controller.map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.service.MapServiceImpl;

import java.util.List;

@RestController
public class CardBenefitsController {
    private final MapServiceImpl cardBenefitsService;

    public CardBenefitsController(MapServiceImpl cardBenefitsService) {
        this.cardBenefitsService = cardBenefitsService;
    }

    @GetMapping("universe/map")
    public List<LocalCardBenefitsDTO> getCardBenefitsByCategory(@RequestParam String category) {
        return cardBenefitsService.findCardBenefitsByCategory(category);
    }
}
