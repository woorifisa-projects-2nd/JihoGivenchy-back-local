package project.local.dto.local;

import lombok.*;
<<<<<<< HEAD
import project.local.dto.cardDetails.CardDetailDTO;
=======
import project.local.dto.CardDetailDTO.CardDetailDTO;
>>>>>>> d122f3abc6c03d65a55558f9d46dd9f48a36c77c

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
<<<<<<< HEAD

=======
>>>>>>> d122f3abc6c03d65a55558f9d46dd9f48a36c77c
}
