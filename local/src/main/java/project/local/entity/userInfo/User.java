package project.local.entity.userInfo;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "USER")
@Builder
//회원정보
public class User {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_NICKNAME", nullable = false)
    private String nickName;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "JOIN_DATE", nullable = false)
    private Date joinDate;

    // 컬럼 추가
    @Column(name = "ROLE", nullable = false)
    private String  role;

    @Column(name = "REAL_PAY", nullable = true)
    private int realPay;

    @Column(name = "RESULT_PAY", nullable = true)
    private int resultPay;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setName(String name) {
        this.name = name;
    }
}
