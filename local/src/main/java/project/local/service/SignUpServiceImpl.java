package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.loginAndSingUp.UserDTO;
import project.local.entity.userInfo.User;
import project.local.repository.UserRepository;
import project.local.service.inter.SignUpService;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void signUp(UserDTO userDTO) {
        System.out.println(userDTO);

        User data = User.builder()
                .id(userDTO.getId())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .nickName(userDTO.getNickName())
                .name(userDTO.getName())
                .joinDate(Date.valueOf(LocalDate.now()))
                .role("ROLE_USER")
                .build();

        Boolean ieExist = userRepository.existsById(userDTO.getId());

        if (ieExist) {
            return;
        }
        System.out.println(data);
        userRepository.save(data);

    }
}
