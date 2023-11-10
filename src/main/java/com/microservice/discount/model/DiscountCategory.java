package com.microservice.discount.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discount_category")
public class DiscountCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category", foreignKey = @ForeignKey(name = "FK_Category_DiscountCategory"))
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_discount", foreignKey = @ForeignKey(name = "FK_Discount_CategoryDiscount"))
    private Discount discount;
}
