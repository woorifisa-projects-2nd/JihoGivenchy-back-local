package project.local.entity.cardInfo;

import lombok.*;
import project.local.entity.cardInfo.CardCompany;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "SUBSCRIPTION_BENEFITS")
@Builder
//구독정보
public class SubscriptionBenefits {

    @Id
    @Column(name = "SUBSCRIPTION_ID", nullable = false)
    private Long id;

    @Column(name = "SUBSCRIPTION_CATEGORY", nullable = false)
    private String category;

    @Column(name = "SUBSCRIPTION_TITLE", nullable = false)
    private String title;

    @Column(name = "SUBSCRIPTION_SUMMARY", nullable = false)
    private String summary;

    @Column(name = "SUBSCRIPTION_FEE", nullable = false)
    private String fee;

    @Column(name = "SUBSCRIPTION_COMPANY_IMAGE", nullable = false)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_NAME")
    private CardCompany cardCompany;

}
