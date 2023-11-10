package com.microservice.discount.service.impl;

import com.microservice.discount.mapper.DiscountMapper;
import com.microservice.discount.model.*;
import com.microservice.discount.repository.DiscountCategoryRepository;
import com.microservice.discount.service.CategoryService;
import com.microservice.discount.service.DiscountProcessService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DiscountProcessServiceServiceImpl implements DiscountProcessService {
    private  final DiscountCategoryRepository discountCategoryRepository;

    private final CategoryService categoryService;

    public DiscountResp getDiscountByCategory(DiscountReq discountReq){

        CategoryResponse categoriesByExternalId = categoryService.getCategoriesByExternalId(discountReq);
        Category category = categoriesByExternalId.getCategory();

        DiscountCategory discountCategoryByCategoryId = discountCategoryRepository.findDiscountCategoryByCategory_Id(category.getId());
        /* DiscountCategory discountCategoryByCategory = discountCategoryRepository.findDiscountCategoryByCategory(category);


        */
        Discount discount = discountCategoryByCategoryId.getDiscount();

        DiscountResp discountResp=null;

        if (Objects.nonNull(discount)) {
            DiscountDto discountDto = DiscountMapper.INSTANCE.discountToDiscountDTO(discount);
            BigDecimal newPrice = discountProcess(discountReq.getPrice(), discountDto.getDiscountPrice());

             discountResp = DiscountResp.builder()
                    .discount(discountDto)
                    .oldPrice(discountReq.getPrice())
                    .newPrice(newPrice)
                    .build();
        }else {
            discountResp=  DiscountResp.builder()
                    .oldPrice(discountReq.getPrice())
                    .newPrice(discountReq.getPrice())
                    .discount(DiscountDto.builder()
                            .code(discountReq.getCode())
                            .build())
                    .build();

        }
            return discountResp;
    }

    private BigDecimal discountProcess(BigDecimal oldPrice, BigDecimal discountPrice) {
       return oldPrice.subtract(discountPrice);
    }










}
