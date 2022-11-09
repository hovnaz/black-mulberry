package com.black.mulberry.data.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryProductResponse {

    private long id;
    private String name;
    private CategoryParentResponse categoryParent;
}
