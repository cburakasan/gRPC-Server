package com.microservice.discount.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllCategoryResp implements Serializable {
    private List<CategoryDto> categoryList;
}



