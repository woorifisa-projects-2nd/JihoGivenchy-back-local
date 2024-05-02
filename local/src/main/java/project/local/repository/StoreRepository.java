package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.local.entity.storeInfo.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {

}
