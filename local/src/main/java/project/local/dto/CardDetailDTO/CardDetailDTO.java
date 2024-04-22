package project.local.dto.CardDetailDTO;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardDetailDTO {

    private Long benefitsId;
    private String benefitTitle;
    private String benefitSummary;
    private String cardCompany;
    private String cardImage;

}
