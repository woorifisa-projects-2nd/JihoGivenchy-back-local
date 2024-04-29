package project.local.controller.map;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.map.StoreDTO;
import project.local.service.MapServiceImpl;
import project.local.service.MyDataServiceImpl;
import project.local.service.UserServiceImpl;

import java.io.IOException;
import java.util.List;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/map")
public class MapBenefitsController {

    private final MapServiceImpl mapService;
    private final MyDataServiceImpl myDataService;
    private final UserServiceImpl userService;

    @GetMapping
    public List<LocalCardBenefitsDTO> getCardBenefitsByCategory(@RequestParam String category) throws IOException, InterruptedException {
    // 로그인 했을 때 userID를 어떻게 꺼낼지 확인해야 함.
        List<CardsDTO> cardsDTOS = myDataService.requestCards(1012341234L);
        String categoryByCode = mapService.getCategoryByCode(category);
        return mapService.findCardByCategory(categoryByCode, cardsDTOS);
    }

    @GetMapping("/store")
    public List<StoreDTO> findAllStore() {
        List<StoreDTO> storeDTOS = mapService.findAllStore();
        return storeDTOS;
    }
}