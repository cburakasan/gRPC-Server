package com.microservice.discount.service;

import com.microservice.discount.model.AddDiscountRequest;

public interface DiscountService {
    void addDiscount(AddDiscountRequest addDiscountRequest);
}
