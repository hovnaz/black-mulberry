package com.black.mulberry.data.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponse {

    private long id;
    private String title;
    private BigDecimal price;
    private int stock;
    private String description;
    private String picUrl;
    private CategoryProductResponse categoryProduct;
    private UserResponse user;
}
