package project.local.entity.userInfo;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
@Table(name = "INQUIRY")
//문의내역
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INQUIRY_ID", nullable = false)
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "INQUIRY_CATEGORY", nullable = false)
    private String category;

    @Column(name = "INQUIRY_TITLE", nullable = false)
    private String title;

    @Column(name = "INQUIRY_CONTENT", nullable = false)
    private String content;

    @Column(name = "IS_ANSWER", nullable = false)
    private int isAnswer;

    @Column(name = "INQUIRY_DATE", nullable = false)
    private Date inquiryDate;

    @Column(name = "INQUIRY_VIEWS", nullable = false)
    private int views;

}
