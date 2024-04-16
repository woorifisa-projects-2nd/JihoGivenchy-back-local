package project.local.dto.mypage;

import lombok.*;

import java.sql.Date;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MySubscriptionDTO {

    private String category;
    private String title;
    private String summary;
    private String fee;
    private String cardCompanyImage;
    private String companyImage;
    private Date issueDate;
    private Date expirationDate;
}
