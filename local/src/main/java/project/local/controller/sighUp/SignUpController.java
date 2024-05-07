package project.local.controller.sighUp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.loginAndSingUp.UserDTO;
import project.local.service.SignUpServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/signUp")
public class SignUpController {

    private final SignUpServiceImpl signUpService;

    @PostMapping
    public void signUp(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getId());
        signUpService.signUp(userDTO);
    }


}
