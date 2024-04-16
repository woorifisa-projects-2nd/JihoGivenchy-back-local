package project.local.dto.local;

import lombok.*;
import project.local.entity.cardInfo.CardCompany;

import javax.persistence.*;

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
    private CardCompany cardCompany;
    private String cardImage;


}
