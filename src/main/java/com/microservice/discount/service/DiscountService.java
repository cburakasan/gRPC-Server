package com.microservice.discount.service;

import com.microservice.discount.model.Discount;

import java.util.List;

public interface DiscountService {

    List<Discount> addDiscountList(List<Discount> discountList);

    Discount addDiscount(Discount discount);
}
