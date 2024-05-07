package project.local.controller.help;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import project.local.dto.mypage.HelpDTO;
import project.local.service.UserServiceImpl;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/csmyq")
public class HelpController {

    private final UserServiceImpl userService;
    // user의 질문 리스트 조회(처음 화면 랜더링 시) 1(답변완료)인 애들부터 조회
    // isAnswer을 같이 보내서 프론트에서 얘네가지고 버튼 다르게
    // questionId를 key로 ㄱㄱㄱ index를 key 말고
    @GetMapping("/{userId}")
    public List<HelpDTO> findHelps(@PathVariable("userId") Long id) {
        return userService.findHelps(id);
    }

    // user의 질문 등록  -> isAnswer은 0으로 등록
    @PostMapping("/add/{userId}")
    public void requestHelp(@RequestBody HelpDTO helpDTO, @PathVariable("userId") Long id) {
        userService.saveHelp(helpDTO, id);
    }

    // 버튼 눌러서 특정 질문 보기
    @GetMapping("/select/{questionId}")
    public HelpDTO findHelp(@PathVariable("questionId") Long id) {
        return userService.findHelp(id);
    }

    // questionId를 받아와서 삭제
    @DeleteMapping("/delete/{questionId}")
    public void deleteHelp(@PathVariable("questionId") Long id) {
        userService.deleteHelp(id);
    }
}
