package project.local.dto.store;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class StoresInfoDTO {
    private Long id;
    private String storeName;
    private String storeAddress;
    private String storeCategory;
    private String storeExDate;
    private String storeRegDate;

    }

