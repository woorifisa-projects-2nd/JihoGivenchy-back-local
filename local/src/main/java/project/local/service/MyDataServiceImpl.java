package project.local.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.mydata.BillsDTO;
import project.local.dto.mydata.BillsDetailsDTO;
import project.local.dto.mydata.CardsDTO;
import project.local.dto.mydata.SubscriptionDTO;
import project.local.service.inter.MyDataService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyDataServiceImpl implements MyDataService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<CardsDTO> requestCards(Long id) throws IOException, InterruptedException {
        String userId = String.valueOf(id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://3.34.123.186:8080/v2/card/cards"))
                .header("userId", userId) // 헤더에 userId 추가
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 CardsDTO 리스트로 변환
        List<CardsDTO> cards = objectMapper.readValue(response.body(), new TypeReference<List<CardsDTO>>() {
        });

        return cards;
    }

    @Override
    public List<BillsDTO> requestBills(Long id) throws IOException, InterruptedException {
        String userId = String.valueOf(id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://3.34.123.186:8080/v2/card/bills"))
                .header("userId", userId) // 헤더에 userId 추가
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 BillsDetailDTO 리스트로 변환
        List<BillsDTO> bills = objectMapper.readValue(response.body(), new TypeReference<List<BillsDTO>>() {
        });

        return bills;
    }

    @Override
    public List<BillsDetailsDTO> requestBillsDetails(Long id, int month) throws IOException, InterruptedException {
        String userId = String.valueOf(id);

        // month 파라미터를 URL 쿼리 파라미터로 추가
        String url = String.format("http://3.34.123.186:8080/v2/card/bills/detail?month=%d", month);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("userId", userId) // 헤더에 userId 추가
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 BillsDetailDTO 리스트로 변환
        List<BillsDetailsDTO> billsDetails = objectMapper.readValue(response.body(), new TypeReference<List<BillsDetailsDTO>>() {
        });

        return billsDetails;
    }

    @Override
    public List<SubscriptionDTO> requestSubscription(Long id) throws IOException, InterruptedException {
        String userId = String.valueOf(id);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://3.34.123.186:8080/v2/subs"))
                .header("userId", userId) // 헤더에 userId 추가
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답을 CardsDTO 리스트로 변환
        List<SubscriptionDTO> subscriptionDTOs = objectMapper.readValue(response.body(), new TypeReference<List<SubscriptionDTO>>() {
        });

        return subscriptionDTOs;
    }



}
