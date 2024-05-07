package project.local.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.entity.userInfo.AnnualDiscount;
import project.local.repository.AnnualDiscountRepository;


@Service
@RequiredArgsConstructor
public class AnnualDiscountService {

    private final AnnualDiscountRepository annualDiscountRepository;

    public AnnualDiscount findById(Long id) {
        return annualDiscountRepository.findById(id).orElse(null);
    }
}
