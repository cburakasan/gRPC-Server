package com.microservice.discount.service;

import com.microservice.discount.model.*;

import java.util.List;

public interface CategoryService {
    CategoryResponse getCategoriesByExternalId(DiscountReq discountReq);

    void updateCategoryId();

    AllCategoryResp getAllCategories();

    List<Category> addCategoryList(List<Category> categories);

    Category addCategory(Category category);
}
