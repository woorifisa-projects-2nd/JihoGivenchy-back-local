package project.local.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import project.local.dto.local.LocalCardDTO;
import project.local.service.AdminServiceImpl;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/admin")
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

}
