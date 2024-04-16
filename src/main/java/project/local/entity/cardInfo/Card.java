package project.local.entity.cardInfo;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "CARD")
//카드정보
public class Card {

    @Id
    @Column(name = "CARD_ID", nullable = false)
    private Long id;

    @Column(name = "CARD_NAME", nullable = false)
    private String cardName;

    @Column(name = "CARD_TYPE", nullable = false)
    private String cardType;

    @Column(name = "ANNUAL_FEE", nullable = false)
    private String annualFee;

    @Column(name = "PREVIOUS_AMOUNT", nullable = false)
    private String previousAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_NAME")
    private CardCompany cardCompany;

    @Column(name = "CARD_IMAGE", nullable = false)
    private String cardImage;

//    @Column(name = "CARD_URL", nullable = false)
//    private String cardURL;

//    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Singular
//    private List<CardBenefits> cardBenefits;

}
