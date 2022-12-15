package com.black.mulberry.data.transfer.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class ProductFilterRequest {
    @NotEmpty
    private String title;
    @Min(0)
    private Long minPrice;
    @Min(1)
    private Long maxPrice;
}
