package com.microservice.discount.service;

import com.microservice.discount.model.AllCategoryResp;
import com.microservice.discount.model.CategoryResponse;
import com.microservice.discount.model.DiscountReq;

public interface CategoryService {
    CategoryResponse getCategoriesByExternalId(DiscountReq discountReq);

    void updateCategoryId ();

    AllCategoryResp getAllCategories();
}
