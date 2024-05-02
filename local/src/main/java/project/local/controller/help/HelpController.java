package project.local.controller.help;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.mypage.HelpDTO;
import project.local.service.UserServiceImpl;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/csmyq")
public class HelpController {

    private final UserServiceImpl userService;

    @PostMapping("/{userId}")
    public void requestHelp(@PathVariable HelpDTO helpDTO) {
        userService.saveHelp(helpDTO);
    }
}
