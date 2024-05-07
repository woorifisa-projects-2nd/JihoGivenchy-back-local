package project.local.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import project.local.dto.local.LocalCardDTO;
import project.local.dto.loginAndSingUp.UserDTO;
import project.local.dto.mypage.HelpDTO;
import project.local.service.AdminServiceImpl;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/manager")
public class AdminController {

    private final AdminServiceImpl adminService;

    @GetMapping("/cards")
    public List<LocalCardDTO> findCards() {
        return adminService.findCards();
    }

    @PostMapping("/cards/add")
    public void addCard(@RequestBody LocalCardDTO localCardDTO) {
        adminService.saveCard(localCardDTO);
    }

    @GetMapping("/cards/update/{cardId}")
    public LocalCardDTO findForUpdate(@PathVariable("cardId") Long id) {
        return adminService.findForUpdate(id);
    }

    @PostMapping("/cards/update")
    public void updateCard(@RequestBody LocalCardDTO localCardDTO) {
        adminService.updateCard(localCardDTO);
        adminService.updateBenefits(localCardDTO);
    }

    @DeleteMapping("/cards/delete/{cardId}")
    public void deleteCard(@PathVariable("cardId") Long id) {
        adminService.deleteCard(id);
    }

    ///////// 여기부터 프론트 연결 ㄱㄱ
    // 답변이 안 된 놈들부터 ㄱㄱ
    @GetMapping("/question")
    public List<HelpDTO> findHelps() {
        return adminService.findHelps();
    }

    // 답변 전이든 뭐든 그 버튼 눌렀을 때, 사용자의 질문 내용 보기
    @GetMapping("/question/{questionId}")
    public HelpDTO findForAnswer(@PathVariable("questionId") Long id) {
        return adminService.findForAnswerHelp(id);
    }

    // 답변 작성하고 저장하기 눌렀을 때
    @PostMapping("/question/answer/{questionId}")
    public void answerHelp(@RequestBody String answer, @PathVariable("questionId") Long id) {
        adminService.answerTheQuestion(answer, id);
    }

    @DeleteMapping("/question/delete/{questionId}")
    public void deleteHelp(@PathVariable("questionId") Long questionId) {
        adminService.deleteHelp(questionId);
    }
    
    @GetMapping("/custom")
    public List<UserDTO> findUsers(){
        return adminService.findUsers();
    }

    @GetMapping("/custom/update/{userId}")
    public UserDTO findForUpdateUser(@PathVariable("userId") Long id) {
        return adminService.findForUpdateUser(id);
    }

    @PostMapping("/custom/update")
    public void updateUser(@RequestBody UserDTO userDTO) {
        adminService.udpateUser(userDTO);
    }

    @DeleteMapping("/custom/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Long id){
        adminService.deleteUser(id);
    }

}
