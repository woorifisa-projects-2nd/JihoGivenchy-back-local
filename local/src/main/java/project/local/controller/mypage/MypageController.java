package project.local.controller.mypage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import project.local.dto.local.LocalCardDTO;
import project.local.dto.loginAndSingUp.CustomUserDetails;
import project.local.dto.loginAndSingUp.UserDTO;
import project.local.dto.mydata.BillsDTO;
import project.local.dto.mydata.BillsDetailsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.mypage.MypageDTO;
import project.local.dto.mypage.SpentAmountDTO;
import project.local.dto.mypage.TimeAndTotalAmountDTO;
import project.local.entity.userInfo.AnnualDiscount;
import project.local.service.AnnualDiscountService;
import project.local.service.MyDataServiceImpl;
import project.local.service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/universe/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MyDataServiceImpl myDataService;
    private final UserServiceImpl userService;
    private final AnnualDiscountService annualDiscountService;
    LocalDate now = LocalDate.now();

    @GetMapping
    public ResponseEntity<?> getMypageData(HttpSession session) throws IOException, InterruptedException {
        // 세션에서 사용자 정보를 가져옵니다.
        UserDetails sessionUser = (CustomUserDetails) session.getAttribute("USER");
        System.out.println(sessionUser.getUsername());
        // 세션에서 사용자 ID를 추출합니다.
        Long userId = sessionUser != null ? Long.valueOf(sessionUser.getUsername()) : null;

        if (userId == null) {
            // 로그인하지 않았거나 세션에서 사용자 정보를 가져올 수 없는 경우
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        List<CardsDTO> cards = myDataService.requestCards(userId);
        List<BillsDTO> bills = myDataService.requestBills(userId);
        TimeAndTotalAmountDTO timeAndTotalAmountDTO = userService.getTimeAndTotalAmount(bills, LocalDate.now());
        List<BillsDetailsDTO> billsDetails = myDataService.requestBillsDetails(userId, timeAndTotalAmountDTO.getMonth());
        List<LocalCardDTO> myCards = userService.findMyCardLists(cards);
        SpentAmountDTO spentAmount = userService.findSpentAmount(billsDetails);
        String categoryCodeFromValue = userService.getCategoryCodeFromValue(spentAmount.getMaxCategoryValue());
        AnnualDiscount annualDiscount = annualDiscountService.findById(userId);

        MypageDTO myPageDTO = MypageDTO.builder()
                .timeAndTotalAmountDTO(timeAndTotalAmountDTO)
                .spentAmountDTO(spentAmount)
                .myCards(myCards)
                .maxCategoryCode(categoryCodeFromValue)
                .annualDiscount(annualDiscount)
                .build();

        return ResponseEntity.ok(myPageDTO);
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