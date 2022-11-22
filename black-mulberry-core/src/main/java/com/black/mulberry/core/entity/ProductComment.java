package com.black.mulberry.core.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_comment")
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    private User user;
    @ManyToOne
    private Product product;
    @Size(min = 3, max = 600)
    @NotNull(message = "Content Is Not Null")
    private String content;
    private Boolean isDelete;
}
