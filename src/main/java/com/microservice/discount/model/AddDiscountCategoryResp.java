package com.microservice.discount.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddDiscountCategoryResp implements Serializable {
    private boolean result;
    private String resultExp;
}