package project.local.dto.mydata;

import lombok.*;
import java.sql.Date;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BillsDetailsDTO {

    private Long chargeDetailId;
    private int cardId;
    private Date paidDay;
    private int paidAmt;
    private String currencyCode;
    private String merchantName;
    private String merchantType;
    private Long chargeId;

}
