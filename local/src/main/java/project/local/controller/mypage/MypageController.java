package project.local.controller.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import project.local.dto.local.LocalCardDTO;
import project.local.dto.mydata.BillsDTO;
import project.local.dto.mydata.BillsDetailsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.mypage.MypageDTO;
import project.local.dto.mypage.SpentAmountDTO;
import project.local.dto.mypage.TimeAndTotalAmountDTO;
import project.local.service.MyDataServiceImpl;
import project.local.service.UserServiceImpl;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/universe/mypage/{userId}")
@RequiredArgsConstructor
public class MypageController {

    private final MyDataServiceImpl myDataService;
    private final UserServiceImpl userService;
    LocalDate now = LocalDate.now();

    @GetMapping
    public MypageDTO forMypage(@PathVariable("userId") Long id) throws Exception {
        Long userId = userService.findUser(id);

        // id를 header에 넣어서 api 요청 -> 내 카드 리스트 반환
        List<CardsDTO> cards = myDataService.requestCards(userId);

        // id를 header에 넣어서 api 요청 -> 내 년, 월 별 청구내역 반환
        List<BillsDTO> bills = myDataService.requestBills(userId);

        //현재시간을 기반으로 전월에 대한 데이터 뽑기 위한 준비작업
        TimeAndTotalAmountDTO dto = userService.getTimeAndTotalAmount(bills, now);

        // id를 header, 년월을 파라미터로 api 요청 -> 특정 달의 청구 상세내역 반환
        List<BillsDetailsDTO> billsDetails = myDataService.requestBillsDetails(userId, dto.getMonth());

        List<LocalCardDTO> myCards = userService.findMyCardLists(cards);

        SpentAmountDTO spentAmount = userService.findSpentAmount(billsDetails);

        String categoryCodeFromValue = userService.getCategoryCodeFromValue(spentAmount.getMaxCategoryValue());

        return MypageDTO.builder()
                .timeAndTotalAmountDTO(dto)
                .spentAmountDTO(spentAmount)
                .myCards(myCards)
                .maxCategoryCode(categoryCodeFromValue)
                .build();
    }
}