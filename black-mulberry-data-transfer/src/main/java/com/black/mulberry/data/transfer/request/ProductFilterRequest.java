package com.black.mulberry.data.transfer.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ProductFilterRequest {

    @Min(value = 0, message = "minimal price should be at least 0")
    private Long minPrice;
    @Min(value = 1, message = "maximum price should be at least 1")
    private Long maxPrice;
}
