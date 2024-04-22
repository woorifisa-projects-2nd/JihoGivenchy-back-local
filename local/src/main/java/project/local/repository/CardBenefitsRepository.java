package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.local.entity.cardInfo.CardBenefits;

import java.util.List;

@Repository
public interface CardBenefitsRepository extends JpaRepository<CardBenefits, Long> {

    List<CardBenefits> findByCard_Id(Long cardId);

    CardBenefits findByCard_IdAndCategoryMapContaining(Long cardId, String category);

}
