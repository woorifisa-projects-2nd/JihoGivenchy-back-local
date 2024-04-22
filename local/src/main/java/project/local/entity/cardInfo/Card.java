package project.local.entity.cardInfo;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "CARD")
//카드정보
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_ID")
    private Long id;

    @Column(name = "CARD_NAME")
    private String cardName;

    @Column(name = "CARD_TYPE")
    private String cardType;

    @Column(name = "ANNUAL_FEE")
    private String annualFee;

    @Column(name = "PREVIOUS_AMOUNT")
    private String previousAmount;

    @Column(name = "COMPANY_NAME")
    private String cardCompany;

    @Column(name = "CARD_IMAGE")
    private String cardImage;

<<<<<<< HEAD
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardBenefits> benefits;

=======
>>>>>>> d122f3abc6c03d65a55558f9d46dd9f48a36c77c
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setAnnualFee(String annualFee) {
        this.annualFee = annualFee;
    }

    public void setPreviousAmount(String previousAmount) {
        this.previousAmount = previousAmount;
    }

    public void setCardCompany(String cardCompany) {
        this.cardCompany = cardCompany;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }
}
