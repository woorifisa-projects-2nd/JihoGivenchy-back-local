package project.local.dto.mypage;

import lombok.*;
import project.local.dto.mydata.SubscriptionDTO;

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
    private List<String> images;

    private List<MySubscriptionDTO> mySubscriptionDTO;

    private List<MySubscriptionDTO> recommendedSubDTO;


}
