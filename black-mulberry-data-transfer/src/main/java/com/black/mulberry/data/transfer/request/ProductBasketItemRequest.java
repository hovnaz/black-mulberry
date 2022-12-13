package com.black.mulberry.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductBasketItemRequest {

    @Min(1)
    private int quantity;
    @Min(1)
    private long productId;
}
