package project.local.controller.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/universe/login")
public class LoginController {

    @GetMapping
    public String loginP(){
        return "login";
    }
}
