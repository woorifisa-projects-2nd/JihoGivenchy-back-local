package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.local.entity.cardInfo.Card;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCardType(String cardType);

    List<Card> findAllByOrderByIdDesc();

//    List<Card> findByIdGreaterThan(Long cursorId, Pageable pageable);

}
