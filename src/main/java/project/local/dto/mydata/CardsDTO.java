package project.local.dto.mydata;

import lombok.*;
import java.sql.Date;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardsDTO {

    private Long cardId;
    private String cardNumber;
    private String cardName;
    private String cardType;
    private String cardBrand;
    private int annualFee;
    private Date issueDate;
    private Date expirationDate;
    private int minimumUsage;
    private Long userId;

}
