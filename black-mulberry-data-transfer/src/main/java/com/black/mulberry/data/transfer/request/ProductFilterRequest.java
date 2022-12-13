package com.black.mulberry.data.transfer.request;

import com.black.mulberry.data.transfer.response.CategoryProductResponse;
import lombok.Data;

@Data
public class ProductFilterRequest {

    private String title;
    private long minPrice;
    private long maxPrice;
}
