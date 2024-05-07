package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.userInfo.Inquiry;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    List<Inquiry> findByUser_IdOrderByIsAnswerDesc(Long userId);

    List<Inquiry> findAllByOrderByIsAnswerAsc();

}
