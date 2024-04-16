package project.local.service.inter;

import project.local.dto.mydata.CardsDTO;
import project.local.dto.mypage.MySubscriptionDTO;
import project.local.dto.mypage.SpentAmountDTO;

import java.util.List;

public interface SubscriptionService {

    List<MySubscriptionDTO> recommendSub(SpentAmountDTO spentAmountDTO, List<CardsDTO> cards);
}
