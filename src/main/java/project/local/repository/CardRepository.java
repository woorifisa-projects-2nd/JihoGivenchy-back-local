package project.local.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.cardInfo.Card;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCardType(String cardType);

//    List<Card> findByIdGreaterThan(Long cursorId, Pageable pageable);
}
