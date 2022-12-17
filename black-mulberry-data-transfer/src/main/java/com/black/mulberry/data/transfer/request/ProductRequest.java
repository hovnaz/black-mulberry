package com.black.mulberry.data.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {
    private Long id;
    @NotBlank(message = "product's title can't be null or empty")
    private String title;
    @Min(1)
    private BigDecimal price;
    @Min(1)
    private int stock;
    private String picUrl;
    @Size(min = 3, max = 600, message = "content length is between 3-600")
    private String description;
    @Min(1)
    private long categoryProductId;
}
