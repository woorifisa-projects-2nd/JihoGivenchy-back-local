package project.local.dto.mypage;

import lombok.*;
import project.local.dto.local.LocalCardDTO;
import project.local.entity.userInfo.AnnualDiscount;
import project.local.entity.userInfo.User;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MypageDTO {

    private TimeAndTotalAmountDTO timeAndTotalAmountDTO;

    private SpentAmountDTO spentAmountDTO;
    // 내 카드 리스트
    private List<LocalCardDTO> myCards;

    private String maxCategoryCode;

    private AnnualDiscount annualDiscount;

    private User user;


}
