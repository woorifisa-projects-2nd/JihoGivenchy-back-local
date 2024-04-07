package project.local.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.User;

public interface UserInfoRepository extends JpaRepository<User, Integer> {

}
