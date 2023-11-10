package com.microservice.discount.service;

import com.microservice.discount.model.DiscountReq;
import com.microservice.discount.model.DiscountResp;

public interface DiscountProcessService  {
    DiscountResp getDiscountByCategory(DiscountReq discountReq);
}
