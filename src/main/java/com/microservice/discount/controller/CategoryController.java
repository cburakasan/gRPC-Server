package com.microservice.discount.controller;
import com.microservice.discount.model.AllCategoryResp;
import com.microservice.discount.service.CategoryService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<AllCategoryResp> getAllDiscountCategory() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/update")
    public void categoryIdUpdate(){
        categoryService.updateCategoryId();
    }
}
