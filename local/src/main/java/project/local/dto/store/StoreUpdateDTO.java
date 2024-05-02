package project.local.dto.store;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StoreUpdateDTO {
    private String storeName;
    private String storeAddress;
    private String storeCategory;
    private Date storeRegDate;
    private Date storeExDate;
}
