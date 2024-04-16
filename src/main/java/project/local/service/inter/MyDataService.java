package project.local.service.inter;

import project.local.dto.mydata.BillsDTO;
import project.local.dto.mydata.BillsDetailsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.mydata.SubscriptionDTO;

import java.util.List;

public interface MyDataService {

    List<CardsDTO> requestCards(Long id) throws Exception;

    List<BillsDTO> requestBills(Long id) throws Exception;

    List<BillsDetailsDTO> requestBillsDetails(Long id, int month) throws Exception;

    public List<SubscriptionDTO> requestSubscription(Long id) throws Exception;
}
