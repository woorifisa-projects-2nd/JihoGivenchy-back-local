package project.local.dto.local;

import lombok.*;
import project.local.entity.cardInfo.Card;

import java.util.List;

// !!!!!! 카드 혜택별로 서칭하기 위한 DTO임 이상한데 쓰지마셍

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LocalCardBenefitsDTO {

    private Long id;
    private String category;
    private String cardType;
    private Long cardId;

    private String categoryMap;
    private String benefitTitleMap;
    private String benefitSummaryMap;
    private String cardName;
    private String image;
<<<<<<< HEAD
}
=======

}
>>>>>>> d122f3abc6c03d65a55558f9d46dd9f48a36c77c
