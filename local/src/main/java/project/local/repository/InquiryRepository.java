package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.userInfo.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
