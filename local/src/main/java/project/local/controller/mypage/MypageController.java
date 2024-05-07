package project.local.controller.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import project.local.dto.local.LocalCardDTO;
import project.local.dto.loginAndSingUp.UserDTO;
import project.local.dto.mydata.BillsDTO;
import project.local.dto.mydata.BillsDetailsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.mypage.MypageDTO;
import project.local.dto.mypage.SpentAmountDTO;
import project.local.dto.mypage.TimeAndTotalAmountDTO;
//import project.local.service.AnnualBenefitsService;
import project.local.entity.userInfo.AnnualDiscount;
import project.local.service.AnnualDiscountService;
import project.local.service.MyDataServiceImpl;
import project.local.service.UserServiceImpl;
import project.local.service.inter.MyDataService;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/universe/mypage")
@RequiredArgsConstructor
public class MypageController {

//    private final MyDataService myDataService;

    private final MyDataServiceImpl myDataService;
    private final UserServiceImpl userService;
    private final AnnualDiscountService annualDiscountService;
    LocalDate now = LocalDate.now();

    @GetMapping("/{userId}")
    public MypageDTO forMypage(@PathVariable("userId") Long id) throws Exception {
        Long userId = userService.findUser(id);
        System.out.println("userId = " + userId);

        // id를 header에 넣어서 api 요청 -> 내 카드 리스트 반환
        List<CardsDTO> cards = myDataService.requestCards(userId);
        System.out.println("cards = " + cards);

        // id를 header에 넣어서 api 요청 -> 내 년, 월 별 청구내역 반환
        List<BillsDTO> bills = myDataService.requestBills(userId);
        System.out.println("bills = " + bills);

        //현재시간을 기반으로 전월에 대한 데이터 뽑기 위한 준비작업
        TimeAndTotalAmountDTO dto = userService.getTimeAndTotalAmount(bills, now);
        System.out.println("dto = " + dto);

        // id를 header, 년월을 파라미터로 api 요청 -> 특정 달의 청구 상세내역 반환
        List<BillsDetailsDTO> billsDetails = myDataService.requestBillsDetails(userId, dto.getMonth());
        System.out.println("billsDetails = " + billsDetails);

        List<LocalCardDTO> myCards = userService.findMyCardLists(cards);
        System.out.println("myCards = " + myCards);

        SpentAmountDTO spentAmount = userService.findSpentAmount(billsDetails);
        System.out.println("spentAmount = " + spentAmount);

        String categoryCodeFromValue = userService.getCategoryCodeFromValue(spentAmount.getMaxCategoryValue());
        System.out.println("categoryCodeFromValue = " + categoryCodeFromValue);

        AnnualDiscount annualDiscount = annualDiscountService.findById(id);


        return MypageDTO.builder()
                .timeAndTotalAmountDTO(dto)
                .spentAmountDTO(spentAmount)
                .myCards(myCards)
                .maxCategoryCode(categoryCodeFromValue)
                .annualDiscount(annualDiscount)
                .build();
    }

    @GetMapping("/update/{userId}")
    public UserDTO findForUpdate(@PathVariable("userId") Long id) {
        return userService.findForUpdate(id);
    }

    @PostMapping("/update/{userId}")
    public void updateUser(@RequestBody UserDTO userDTO) {
        System.out.println("userDTO = " + userDTO);
        userService.updateUser(userDTO);
    }
}