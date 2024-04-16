package project.local.dto.mydata;

import lombok.*;

import java.sql.Date;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {

    private Long subscriptionId;
    private Long userId;
    private Date issueDate;
    private Date expirationDate;
}
