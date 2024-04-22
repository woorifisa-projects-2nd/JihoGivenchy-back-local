package project.local.controller.map;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.local.LocalCardBenefitsDTO;
<<<<<<< HEAD
=======
import project.local.dto.map.MapBenefitDTO;
>>>>>>> d122f3abc6c03d65a55558f9d46dd9f48a36c77c
import project.local.service.MapServiceImpl;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/map")
public class MapBenefitsController {
    private final MapServiceImpl mapService;

    @GetMapping
    public LocalCardBenefitsDTO getCardBenefitsByCategory(@RequestParam String category) {

        try {
            String categoryByCode = mapService.getCategoryByCode(category);
            log.info(categoryByCode);
            return mapService.findCardByCategory(categoryByCode, 1012341234L);
        } catch (Exception e) {
            log.info("asdf");
            throw new RuntimeException(e);
        }
    }
}