package project.local.dto.mypage;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HelpDTO {

    private Long questionId;
    private Long userId;

    private String userName;
    private String inquiryTitle;
    private String inquiryCategory;
    private String inquiryContent;
    private int isAnswer;

    private String answer;

}
