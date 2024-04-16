package project.local.service.inter;

import project.local.dto.mydata.BillsDTO;
import project.local.dto.mydata.BillsDetailsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.mydata.SubscriptionDTO;
import project.local.dto.mypage.MySubscriptionDTO;
import project.local.dto.mypage.SpentAmountDTO;
import project.local.dto.mypage.TimeAndTotalAmountDTO;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    Long findUser(Long id);

    List<String> findMyCardLists(List<CardsDTO> cards);

    TimeAndTotalAmountDTO getTimeAndTotalAmount(List<BillsDTO> bills, LocalDate time);

    SpentAmountDTO findSpentAmount(List<BillsDetailsDTO> billsDetails);

    List<MySubscriptionDTO> findMySubscription(List<SubscriptionDTO> subscriptions);
}
