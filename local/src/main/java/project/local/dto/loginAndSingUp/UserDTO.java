package project.local.dto.loginAndSingUp;

import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String password;
    private String nickName;
    private String name;
    private Date joinDate;
    private String role;

    private String grade;

}
