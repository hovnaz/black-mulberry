package com.black.mulberry.data.transfer.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductSearchRequest {

    @NotBlank(message = "title can't be null or empty")
    private String title;
}
