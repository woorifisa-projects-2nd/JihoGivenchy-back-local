package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.local.dto.store.StoreUpdateDTO;
import project.local.dto.store.StoreUploadDTO;
import project.local.dto.store.StoresInfoDTO;
import project.local.entity.storeInfo.Store;
import project.local.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreControllService {
    private final StoreRepository storeRepository;


    //모든 데이터 들고 오기
    public List<StoresInfoDTO> getAllStores(){
        ArrayList<StoresInfoDTO> storeDTOS = new ArrayList<>();
        List<Store> stores = storeRepository.findAll();

        for (Store store : stores) {
            storeDTOS.add(StoresInfoDTO.builder()
                            .id(store.getStoreId())
                            .storeName(store.getStoreName())
                            .storeAddress(store.getStoreAddress())
                            .storeCategory(store.getStoreCategory())
                            .storeRegDate(store.getStoreRegDate().toString())
                            .storeExDate(store.getStoreExDate().toString())
                    .build());
        }

        return storeDTOS;
    }

    //데이터 저장하기
    public Store save(StoreUploadDTO storeUploadDTO) {
        return storeRepository.save(storeUploadDTO.toEntity());
    }


    //업데이트 하기
    @Transactional
    public void update(Long id, StoreUpdateDTO storeUpdateDTO) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        store.update(storeUpdateDTO.getStoreName(), storeUpdateDTO.getStoreAddress(), storeUpdateDTO.getStoreCategory(), storeUpdateDTO.getStoreRegDate(), storeUpdateDTO.getStoreExDate());

        
    }

    //삭제하기
    public void delete(Long id) {
        storeRepository.deleteById(id);
    }



}
