package com.black.mulberry.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCommentRequest {

    @Range(min = 1, message = "product id is invalid")
    private long productId;
    @NotBlank(message = "product is not blank")
    @Size(min = 3, max = 600, message = "content length is between 3-600")
    private String content;
}
