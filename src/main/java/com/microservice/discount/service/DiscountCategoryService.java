package com.microservice.discount.service;

import com.microservice.discount.model.Category;
import com.microservice.discount.model.Discount;
import com.microservice.discount.model.DiscountCategory;

import java.util.List;

public interface DiscountCategoryService {
    void addDiscountAndCategoryList(List<Category> newCategoryListByDb, List<Discount> newDiscountListByDb);

    DiscountCategory findDiscountCategoryByCategory_Id(Integer categoryId);

    void addDiscountCategory(DiscountCategory discountCategory);
}
