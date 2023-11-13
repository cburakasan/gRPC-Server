package com.microservice.discount.service.impl;

import com.microservice.discount.mapper.CategoryMapper;
import com.microservice.discount.mapper.DiscountMapper;
import com.microservice.discount.model.*;
import com.microservice.discount.service.CategoryService;
import com.microservice.discount.service.DiscountCategoryService;
import com.microservice.discount.service.DiscountProcessService;

import com.microservice.discount.service.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DiscountProcessServiceServiceImpl implements DiscountProcessService {
    private final CategoryService categoryService;

    private final DiscountService discountService;

    private final DiscountCategoryService discountCategoryService;

    public DiscountResp getDiscountByCategory(DiscountReq discountReq) {

        CategoryResponse categoriesByExternalId = categoryService.getCategoriesByExternalId(discountReq);
        Category category = categoriesByExternalId.getCategory();

        DiscountCategory discountCategoryByCategoryId = discountCategoryService.findDiscountCategoryByCategory_Id(category.getId());

        Discount discount = discountCategoryByCategoryId.getDiscount();

        DiscountResp discountResp = null;

        if (Objects.nonNull(discount)) {
            DiscountDto discountDto = DiscountMapper.INSTANCE.discountToDiscountDTO(discount);
            BigDecimal newPrice = discountProcess(discountReq.getPrice(), discountDto.getDiscountPrice());

            discountResp = DiscountResp.builder()
                    .discount(discountDto)
                    .oldPrice(discountReq.getPrice())
                    .newPrice(newPrice)
                    .build();
        } else {
            discountResp = DiscountResp.builder()
                    .oldPrice(discountReq.getPrice())
                    .newPrice(discountReq.getPrice())
                    .discount(DiscountDto.builder()
                            .code(discountReq.getCode())
                            .build())
                    .build();

        }
        return discountResp;
    }

    @Override
    public AddDiscountCategoryResp addDiscountCategoryList(AddDiscountCategoryRequest addDiscountCategoryRequest) {
        try {

            List<AddDiscountRequest> categoryList = addDiscountCategoryRequest.getCategoryList();


            List<Discount> discountList = new ArrayList<>();
            List<Category> categories = new ArrayList<>();

            for (AddDiscountRequest addDiscountRequest : categoryList) {
                discountList.add(DiscountMapper.INSTANCE.DiscountDtoToDiscount(addDiscountRequest.getDiscountDto()));
                categories.add(CategoryMapper.INSTANCE.categoryDtoToCategory(addDiscountRequest.getCategoryDto()));
            }

            List<Category> newCategoryListByDb = addCategoryList(categories);

            List<Discount> newDiscountListByDb = addDiscountList(discountList);


            addDiscountAndCategoryList(newCategoryListByDb, newDiscountListByDb);

            return AddDiscountCategoryResp.builder()
                    .result(true)
                    .resultExp("Discount and Category list has been applied.")
                    .build();
        } catch (Exception exception) {
            return AddDiscountCategoryResp.builder()
                    .result(false)
                    .resultExp("Save process is failed.")
                    .build();
        }

    }

    private void addDiscountAndCategoryList(List<Category> newCategoryListByDb, List<Discount> newDiscountListByDb) {
        discountCategoryService.addDiscountAndCategoryList(newCategoryListByDb, newDiscountListByDb);
    }

    private List<Discount> addDiscountList(List<Discount> discountList) {
        return discountService.addDiscountList(discountList);
    }

    private List<Category> addCategoryList(List<Category> categories) {
        return categoryService.addCategoryList(categories);
    }


    private BigDecimal discountProcess(BigDecimal oldPrice, BigDecimal discountPrice) {
        return oldPrice.subtract(discountPrice);
    }


    @Override
    public void addDiscount(AddDiscountRequest addDiscountRequest) {
        Discount discount = getDiscount(addDiscountRequest);
        Discount saveDiscount = discountService.addDiscount(discount);

        Category category = getCategory(addDiscountRequest);
        Category saveCategory = categoryService.addCategory(category);

        DiscountCategory discountCategory = getDiscountCategory(saveCategory, saveDiscount);

        discountCategoryService.addDiscountCategory(discountCategory);

    }

    private DiscountCategory getDiscountCategory(Category saveCategory, Discount saveDiscount) {
        return DiscountCategory.builder()
                .category(saveCategory)
                .discount(saveDiscount)
                .build();
    }

    private Category getCategory(AddDiscountRequest addDiscountRequest) {
        Category category = new Category();
        CategoryDto categoryDto = addDiscountRequest.getCategoryDto();
        if (Objects.nonNull(categoryDto)) {
            category = CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
        }
        return category;
    }

    private Discount getDiscount(AddDiscountRequest addDiscountRequest) {
        Discount discount = new Discount();
        if (Objects.nonNull(addDiscountRequest.getDiscountDto())) {
            discount = DiscountMapper.INSTANCE.DiscountDtoToDiscount(addDiscountRequest.getDiscountDto());
        }
        return discount;
    }

}
