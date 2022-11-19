package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.CategoryProduct;

public interface CategoryProductService {
    /**
     * find categories by id
     * @param categoryId
     * @return CategoryProduct
     */
    CategoryProduct findById(long categoryId);

}
