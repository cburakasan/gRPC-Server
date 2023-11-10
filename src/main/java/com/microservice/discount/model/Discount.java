package com.microservice.discount.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "discounts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Discount {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "discountPrice")
    private BigDecimal discountPrice;

}
