package com.microservice.discount.mapper;


import com.microservice.discount.model.Discount;
import com.microservice.discount.model.DiscountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiscountMapper {

    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

    DiscountDto discountToDiscountDTO(Discount discount);

    Discount DiscountDtoToDiscount(DiscountDto discountDto);
}
