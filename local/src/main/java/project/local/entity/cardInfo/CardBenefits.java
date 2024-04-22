package project.local.entity.cardInfo;

import lombok.*;
import project.local.entity.cardInfo.Card;

import javax.persistence.*;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "CARD_BENEFITS")
//카드혜택요약(카드검색)
public class CardBenefits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_BENEFITS_ID")
    private Long id;

    @Column(name = "CARD_BENEFITS_CATEGORY")
    private String category;

    @Column(name = "CARD_BENEFITS_TITLE")
    private String benefitTitle;

    @Column(name = "CARD_BENEFITS_SUMMARY")
    private String benefitSummary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID")
    private Card card;

    @Column(name = "CARD_BENEFITS_CATEGORY_MAP")
    private String categoryMap;

    @Column(name = "CARD_BENEFITS_TITLE_MAP")
    private String benefitTitleMap;

    @Column(name = "CARD_BENEFITS_SUMMARY_MAP")
    private String benefitSummaryMap;

<<<<<<< HEAD
    public void setCategory(String category) {
        this.category = category;
    }

=======
>>>>>>> d122f3abc6c03d65a55558f9d46dd9f48a36c77c
    public void setBenefitTitle(String benefitTitle) {
        this.benefitTitle = benefitTitle;
    }

    public void setBenefitSummary(String benefitSummary) {
        this.benefitSummary = benefitSummary;
    }
}
