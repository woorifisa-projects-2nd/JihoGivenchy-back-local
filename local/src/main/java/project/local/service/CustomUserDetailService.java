package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.local.dto.loginAndSingUp.CustomUserDetails;
import project.local.entity.userInfo.User;
import project.local.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username 매개변수를 id로 받고, 이를 Long 타입으로 변환
        Long id = Long.valueOf(username);

        // ID를 사용하여 사용자 정보 검색
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        // 검색된 사용자 정보를 바탕으로 CustomUserDetails 객체 생성
        return new CustomUserDetails(user);
    }
}
