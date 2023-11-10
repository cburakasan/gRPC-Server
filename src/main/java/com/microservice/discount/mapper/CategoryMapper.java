package com.microservice.discount.mapper;


import com.microservice.discount.model.Category;
import com.microservice.discount.model.CategoryDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto (Category category);

    Category categoryDtoToCategory(CategoryDto categoryDto);
}
