package com.black.mulberry.core.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal price;
    private int stock;
    private String picUrl;
    private LocalDate createAt;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryProduct categoryProduct;
    @Column(name = "is_delete")
    private boolean isDelete;
    @ManyToOne
    private User user;
}
