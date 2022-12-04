package com.black.mulberry.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_basket")
public class ProductBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isPaid;
    @ManyToOne
    private User user;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ProductBasketItem> productBasketItems;
}
