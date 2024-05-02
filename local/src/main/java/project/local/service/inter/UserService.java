package project.local.service.inter;

import project.local.dto.local.LocalCardDTO;
import project.local.dto.loginAndJoin.UserDTO;
import project.local.dto.mydata.BillsDTO;
import project.local.dto.mydata.BillsDetailsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.mypage.HelpDTO;
import project.local.dto.mypage.SpentAmountDTO;
import project.local.dto.mypage.TimeAndTotalAmountDTO;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    Long findUser(Long id);

    List<LocalCardDTO> findMyCardLists(List<CardsDTO> cards);

    TimeAndTotalAmountDTO getTimeAndTotalAmount(List<BillsDTO> bills, LocalDate time);

    SpentAmountDTO findSpentAmount(List<BillsDetailsDTO> billsDetails);

    String getCategoryCodeFromValue(String categoryValue);

    UserDTO findForUpdate(Long id);

    void updateUser(UserDTO userDTO);

    void saveHelp(HelpDTO helpDTO);
}
