package com.microservice.discount.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CategoryDto implements Serializable {

    private String name;

    private Integer externalId;
}
