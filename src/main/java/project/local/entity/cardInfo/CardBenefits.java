package project.local.entity.cardInfo;

import lombok.*;
import project.local.entity.cardInfo.Card;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "CARD_BENEFITS")
//카드혜택요약(카드검색)
public class CardBenefits {

    @Id
    @Column(name = "CARD_BENEFITS_ID", nullable = false)
    private Long id;

    @Column(name = "CARD_BENEFITS_CATAGORY", nullable = false)
    private String category;

    @Column(name = "CARD_BENEFITS_TITLE", nullable = false)
    private String benefitTitle;

    @Column(name = "CARD_BENEFITS_SUMMARY", nullable = false)
    private String benefitSummary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID")
    private Card card;

}
