package project.local.dto.local;

import lombok.*;
import project.local.dto.cardDetails.CardDetailDTO;
import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LocalCardDTO {

    private Long id;
    private String cardName;
    private String cardType;
    private String annualFee;
    private String previousAmount;
    private String cardCompany;
    private String cardImage;

    private List<CardDetailDTO> benefits;

}
