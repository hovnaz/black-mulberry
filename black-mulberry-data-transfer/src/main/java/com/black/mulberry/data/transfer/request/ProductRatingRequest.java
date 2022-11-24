package com.black.mulberry.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRatingRequest {

    @Min(value = 1, message = "product id is invalid")
    private long productId;
    @Range(min = 1, max = 5, message = "the rating scale is 1 to 5")
    private int rating;
}
