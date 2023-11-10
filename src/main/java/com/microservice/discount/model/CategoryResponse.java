package com.microservice.discount.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CategoryResponse implements Serializable {
    private  Category category;
}
