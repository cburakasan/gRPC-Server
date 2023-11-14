package com.microservice.discount.service.impl;

import com.microservice.discount.model.Category;
import com.microservice.discount.model.Discount;
import com.microservice.discount.model.DiscountCategory;
import com.microservice.discount.repository.DiscountCategoryRepository;
import com.microservice.discount.service.DiscountCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiscountCategoryServiceImpl implements DiscountCategoryService {
    private final DiscountCategoryRepository discountCategoryRepository;

    @Override
    public void addDiscountAndCategoryList(List<Category> newCategoryListByDb, List<Discount> newDiscountListByDb) {

        List<DiscountCategory> discountCategoryList = getDiscountCategoryList(newDiscountListByDb, newCategoryListByDb);
        discountCategoryRepository.saveAll(discountCategoryList);
    }

    @Override
    public DiscountCategory findDiscountCategoryByCategory_Id(Integer categoryId) {

        return discountCategoryRepository.findDiscountCategoryByCategory_Id(categoryId);
    }

    @Override
    public void addDiscountCategory(DiscountCategory discountCategory) {
        discountCategoryRepository.save(discountCategory);
    }

    private List<DiscountCategory> getDiscountCategoryList(List<Discount> discountListByDB, List<Category> categoriesByDB) {
        List<DiscountCategory> discountCategoryList = new ArrayList<>();

        int discountListSize = discountListByDB.size();
        int categoryListSize = categoriesByDB.size();

        if (Objects.equals(discountListSize, categoryListSize) && discountListSize > 0) {
            for (int i = 0; i < discountListSize; i++) {
                discountCategoryList.add(DiscountCategory.builder()
                        .discount(discountListByDB.get(i))
                        .category(categoriesByDB.get(i))
                        .build());
            }
        }
        return discountCategoryList;
    }
}
