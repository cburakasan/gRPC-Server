package com.microservice.discount.controller;

import com.microservice.discount.model.AddDiscountRequest;
import com.microservice.discount.model.DiscountReq;
import com.microservice.discount.model.DiscountResp;
import com.microservice.discount.service.DiscountProcessService;
import com.microservice.discount.service.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountService discountService;

    private final DiscountProcessService discountProcessService;

    @PostMapping("/add")
    public void addDiscount(@RequestBody AddDiscountRequest addDiscountRequest){
        discountService.addDiscount(addDiscountRequest);
    }

    @PostMapping("/get")
    public ResponseEntity<DiscountResp> getDiscountRest(@RequestBody DiscountReq discountReq){
        return ResponseEntity.ok(discountProcessService.getDiscountByCategory(discountReq));
    }
}
