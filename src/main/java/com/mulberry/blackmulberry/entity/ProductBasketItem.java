package com.mulberry.blackmulberry.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_basket_item")
public class ProductBasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    @ManyToOne
    private ProductBasket productBasket;
}
