package com.microservice.discount.service.impl;

import com.microservice.discount.mapper.CategoryMapper;
import com.microservice.discount.model.*;

import com.microservice.discount.repository.CategoryRepository;
import com.microservice.discount.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse getCategoriesByExternalId(DiscountReq discountReq) {
        Category categoriesByExternalId = new Category();

        if (Objects.nonNull(discountReq.getExternalCategoryId())) {
            categoriesByExternalId = categoryRepository.findCategoriesByExternalId(discountReq.getExternalCategoryId());
        }
        return CategoryResponse.builder().category(categoriesByExternalId).build();
    }


    public  void updateCategoryId () {

        List<Category> categories = categoryRepository.findAll();
        Set<Integer> usedExternalIds = new HashSet<>();
        Random random = new Random();

        for (Category category : categories) {
            int randomExternalId;

            do {
                randomExternalId = random.nextInt(100000);
            } while (!usedExternalIds.add(randomExternalId));

            category.setExternalId(randomExternalId);
        }

        categoryRepository.saveAll(categories);
    }

    @Override
    public AllCategoryResp getAllCategories() {

        List<Category> allCategories = categoryRepository.findAll();

        List<CategoryDto> categoryList = allCategories.stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryDto)
                .toList();

        return AllCategoryResp.builder().categoryList(categoryList).build();
    }
}
