package project.local.entity.userInfo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "FAQ")
//FAQ
public class Faq {

    @Id
    @Column(name = "FAQ_ID", nullable = false)
    private Long id;

    @Column(name = "FAQ_TITLE", nullable = false)
    private String title;

    @Column(name = "FAQ_CONTENT", nullable = false)
    private String content;

    @Column(name = "FAQ_VIEWS", nullable = false)
    private int view;

    @Column(name = "FAQ_CATEGORY", nullable = false)
    private String category;

}
