package com.black.mulberry.core.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

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
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    private String content;
    private Boolean isDelete;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate createAt;
    @UpdateTimestamp
    private LocalDate updateAt;
}
