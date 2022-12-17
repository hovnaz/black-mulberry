package com.black.mulberry.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryProductRequest {

    @NotBlank(message = "category product's name can't be null or empty")
    private String name;
    @Min(1)
    private int categoryParentId;
}
