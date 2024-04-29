package project.local.dto.map;

import lombok.*;

import javax.persistence.Column;
import java.sql.Date;

@Builder
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class StoreDTO {
    private Long storeId;
    private String storeName;
    private String storeAddress;
    private String storeCategory;
    private Date storeRegDate;
    private Date storeExDate;

}
