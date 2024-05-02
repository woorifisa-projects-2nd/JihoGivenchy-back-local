package project.local.controller.store;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.local.dto.store.StoreUpdateDTO;
import project.local.dto.store.StoreUploadDTO;
import project.local.dto.store.StoresInfoDTO;
import project.local.entity.storeInfo.Store;
import project.local.service.StoreControllService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreController {
    private final StoreControllService storeControllService;

    @GetMapping("/universe/store")
    public List<StoresInfoDTO> findAllStores() {
        return storeControllService.getAllStores();
    }

    @PostMapping("/universe/store")
    public void saveStore(@RequestBody StoreUploadDTO storeUploadDTO) {
        System.out.println(storeUploadDTO);
        storeControllService.save(storeUploadDTO);
    }


    @PutMapping("/universe/store/{id}")
    public void updateStore(@PathVariable Long id, @RequestBody StoreUpdateDTO storeUpdateDTO) {
      storeControllService.update(id, storeUpdateDTO);
    }

    @DeleteMapping("/universe/store/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeControllService.delete(id);
    }

}
