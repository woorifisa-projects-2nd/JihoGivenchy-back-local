package project.local.controller.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.local.dto.loginAndSingUp.UserDTO;
import project.local.service.CustomUserDetailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/login")
public class LoginController {

    private final CustomUserDetailService customUserDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO, HttpServletRequest request){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(String.valueOf(userDTO.getId()));

        if (bCryptPasswordEncoder.matches(userDTO.getPassword(), userDetails.getPassword())){
            // 세션 생성
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);

            // 세션 ID 반환
            HttpSession session = request.getSession();
            session.setAttribute("USER", userDetails); // 여기서 "USER"는 사용자 정보를 저장하는 키입니다.
            String sessionId = session.getId();

            // 성공 로직 처리 (예: 토큰 발급 등)
            return ResponseEntity.ok().header("Set-Cookie", "SESSIONID=" + sessionId + "; Path=/; HttpOnly").body("login success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login failure");
        }
    }
}
