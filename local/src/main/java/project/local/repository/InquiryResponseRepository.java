package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.userInfo.InquiryResponse;

public interface InquiryResponseRepository extends JpaRepository<InquiryResponse, Long> {
}
