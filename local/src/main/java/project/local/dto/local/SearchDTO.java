package project.local.dto.local;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {

    private List<Long> cardId;
}
