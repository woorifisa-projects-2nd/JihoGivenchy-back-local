package project.local.dto.store;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.local.entity.storeInfo.Store;

import java.sql.Date;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StoreUploadDTO {
    private String storeName;
    private String storeAddress;
    private String storeCategory;
    private Date storeRegDate;
    private Date storeExDate;

    public Store toEntity() {
        return Store.builder()
                .storeName(this.storeName)
                .storeAddress(this.storeAddress)
                .storeCategory(this.storeCategory)
                .storeRegDate(this.storeRegDate)
                .storeExDate(this.storeExDate)
                .build();
    }

}
