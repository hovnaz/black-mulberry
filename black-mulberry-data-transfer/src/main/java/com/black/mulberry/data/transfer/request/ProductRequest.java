package com.black.mulberry.data.transfer.request;

import com.black.mulberry.data.transfer.response.ProductResponse;
import com.black.mulberry.data.transfer.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {
    private String title;
    private BigDecimal price;
    private int stock;
    private String picUrl;
    private String description;
    private long categoryProductId;
}
