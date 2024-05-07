package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.local.entity.userInfo.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 이미 존재하는 ID 인지 검증
    boolean existsById(Long id);

    User findByName(String username);

}
