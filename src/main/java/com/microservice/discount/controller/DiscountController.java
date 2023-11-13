package com.microservice.discount.controller;

import com.microservice.discount.model.*;
import com.microservice.discount.service.DiscountProcessService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountProcessService discountProcessService;

    @PostMapping("/add")
    public void addDiscount(@RequestBody AddDiscountRequest addDiscountRequest) {
        discountProcessService.addDiscount(addDiscountRequest);
    }

    @PostMapping("/get")
    public ResponseEntity<DiscountResp> getDiscountRest(@RequestBody DiscountReq discountReq) {
        return ResponseEntity.ok(discountProcessService.getDiscountByCategory(discountReq));
    }

    @PostMapping("/add/category/list")
    public ResponseEntity<AddDiscountCategoryResp> addDiscountCategory(AddDiscountCategoryRequest addDiscountCategoryRequest) {
        return ResponseEntity.ok(discountProcessService.addDiscountCategoryList(addDiscountCategoryRequest));
    }
}
