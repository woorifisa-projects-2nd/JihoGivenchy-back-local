package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.cardInfo.SubscriptionBenefits;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionBenefits, Long> {

    List<SubscriptionBenefits> findByCategory(String maxCategory);
}
