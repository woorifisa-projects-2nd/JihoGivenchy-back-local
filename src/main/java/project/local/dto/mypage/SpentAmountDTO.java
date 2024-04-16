package project.local.dto.mypage;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpentAmountDTO {

    private int restaurant;
    private int cafe;
    private int gasStation;
    private int shopping;
    private int convenienceStore;
    private int supermarket;
    private int movie;
    private int etc;

    private String maxCategory;
}
