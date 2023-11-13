package com.microservice.discount.repository;

import com.microservice.discount.model.DiscountCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCategoryRepository extends JpaRepository<DiscountCategory, Integer> {
    DiscountCategory findDiscountCategoryByCategory_Id(Integer categoryId);
}
