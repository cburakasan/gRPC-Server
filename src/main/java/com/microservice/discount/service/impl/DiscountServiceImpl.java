package com.microservice.discount.service.impl;

import com.microservice.discount.mapper.CategoryMapper;
import com.microservice.discount.mapper.DiscountMapper;
import com.microservice.discount.model.*;
import com.microservice.discount.repository.CategoryRepository;
import com.microservice.discount.repository.DiscountCategoryRepository;
import com.microservice.discount.repository.DiscountRepository;
import com.microservice.discount.service.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    private final CategoryRepository categoryRepository;

    private final DiscountCategoryRepository discountCategoryRepository;
    @Override
    public void addDiscount(AddDiscountRequest addDiscountRequest) {
        Discount discount = getDiscount(addDiscountRequest);
        Discount saveDiscount = discountRepository.save(discount);

        Category category = getCategory(addDiscountRequest);
        Category saveCategory = categoryRepository.save(category);

        DiscountCategory discountCategory = getDiscountCategory(saveCategory, saveDiscount);

        discountCategoryRepository.save(discountCategory);

    }

    private DiscountCategory getDiscountCategory(Category saveCategory, Discount saveDiscount) {
        return DiscountCategory.builder()
                .category(saveCategory)
                .discount(saveDiscount)
                .build();
    }

    private  Category getCategory(AddDiscountRequest addDiscountRequest) {
        Category category = new Category();
        CategoryDto categoryDto = addDiscountRequest.getCategoryDto();
        if (Objects.nonNull(categoryDto)) {
            category = CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
        }
        return category;
    }

    private  Discount getDiscount(AddDiscountRequest addDiscountRequest) {
        Discount discount = new Discount();
        if (Objects.nonNull(addDiscountRequest.getDiscountDto())){
           discount= DiscountMapper.INSTANCE.DiscountDtoToDiscount(addDiscountRequest.getDiscountDto());
        }
        return discount;
    }
}
