package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.data.transfer.request.CategoryProductRequest;
import com.black.mulberry.data.transfer.response.CategoryProductResponse;

import java.util.List;

public interface CategoryProductService {

    CategoryProduct save(CategoryProductRequest categoryProductRequest);

    List<CategoryProductResponse> findAll();

    CategoryProduct update(long categoryProductId, CategoryProductRequest categoryProductRequest);

    void deleteById(final long id);

    /**
     * find categories by id
     * @param categoryId
     * @return CategoryProduct
     */
    CategoryProduct findById(long categoryId);

}
