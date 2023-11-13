package com.microservice.discount.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddDiscountCategoryRequest implements Serializable {
    private List<AddDiscountRequest> categoryList;
}