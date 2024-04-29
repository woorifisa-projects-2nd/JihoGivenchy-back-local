package project.local.dto.cardDetails;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CardDetailDTO {

    private Long benefitsId;
    private String benefitTitle;
    private String benefitSummary;

    private String cardCompany;
    private String cardImage;
    private String category;

}
