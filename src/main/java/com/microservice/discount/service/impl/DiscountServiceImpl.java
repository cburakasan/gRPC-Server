package com.microservice.discount.service.impl;

import com.microservice.discount.model.*;

import com.microservice.discount.repository.DiscountRepository;
import com.microservice.discount.service.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    @Override
    public List<Discount> addDiscountList(List<Discount> discountList) {
        return discountRepository.saveAll(discountList);
    }

    @Override
    public Discount addDiscount(Discount discount) {
        return discountRepository.save(discount);
    }
}
