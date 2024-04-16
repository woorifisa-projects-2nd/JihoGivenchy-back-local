package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.userInfo.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
