package project.local.entity.storeInfo;

import lombok.*;
import project.local.entity.userInfo.User;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "STORE")
@Builder
// 제휴매장
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private Long storeId;

    @Column(name = "STORE_NAME")
    private String storeName;

    @Column(name = "STORE_ADDRESS")
    private String storeAddress;

    @Column(name = "STORE_CATEGORY")
    private String storeCategory;

    @Column(name = "STORE_REGDATE")
    private Date storeRegDate;

    @Column(name = "STORE_EXDATE")
    private Date storeExDate;

    public void update(String storeName, String storeAddress, String storeCategory, Date storeRegDate, Date storeExDate) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeCategory = storeCategory;
        this.storeRegDate = storeRegDate;
        this.storeExDate = storeExDate;
    }






}
