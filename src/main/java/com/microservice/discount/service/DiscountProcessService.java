package com.microservice.discount.service;

import com.microservice.discount.model.*;

public interface DiscountProcessService {
    DiscountResp getDiscountByCategory(DiscountReq discountReq);

    AddDiscountCategoryResp addDiscountCategoryList(AddDiscountCategoryRequest addDiscountCategoryRequest);

    void addDiscount(AddDiscountRequest addDiscountRequest);

}
