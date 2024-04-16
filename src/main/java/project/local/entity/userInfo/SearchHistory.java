package project.local.entity.userInfo;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
@Table(name = "MAP_SEARCH_HISTORY")
//지도검색
public class SearchHistory {

    @Id
    @Column(name = "SEARCH_HISTORY_ID", nullable = false)
    private Long id;

    @Column(name = "SEARCH_HISTORY", nullable = false)
    private String searchContents;

    @Column(name = "SEARCH_DATE", nullable = false)
    private Date searchDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
