package com.mulberry.blackmulberry.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private int stock;
    private String picUrl;
    private Date createAt;
    private String description;
    @ManyToOne
    @JoinColumn(name= "category_id")
    private CategoryProduct categoryProduct;
    @Column(name = "is_delete")
    private boolean isDelete;
    @ManyToOne
    private User user;

}
