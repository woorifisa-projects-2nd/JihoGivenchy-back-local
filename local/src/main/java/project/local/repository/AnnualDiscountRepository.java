package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.local.entity.userInfo.AnnualDiscount;

@Repository
public interface AnnualDiscountRepository extends JpaRepository<AnnualDiscount, Long> {

}
