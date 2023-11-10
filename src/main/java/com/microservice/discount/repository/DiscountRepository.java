package com.microservice.discount.repository;

import com.microservice.discount.model.Category;
import com.microservice.discount.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Integer> {
}
