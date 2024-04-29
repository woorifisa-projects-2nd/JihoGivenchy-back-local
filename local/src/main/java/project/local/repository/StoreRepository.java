package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.storeInfo.Store;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
