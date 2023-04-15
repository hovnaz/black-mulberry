package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.data.transfer.request.CategoryProductRequest;
import com.black.mulberry.data.transfer.response.CategoryProductResponse;

import java.util.List;

/**
 * A service interface for managing category products.
 */
public interface CategoryProductService {

    /**
     * Saves a new category product based on the provided category product request.
     *
     * @param categoryProductRequest the category product request containing the category product's details
     * @return the newly saved CategoryProduct object
     */
    CategoryProduct save(CategoryProductRequest categoryProductRequest);

    /**
     * Returns a list of all existing category products.
     *
     * @return a list of CategoryProductResponse objects representing all existing category products
     */
    List<CategoryProductResponse> findAll();

    /**
     * Updates an existing category product based on the provided category product ID and category product request.
     *
     * @param categoryProductId the ID of the category product to update
     * @param categoryProductRequest the category product request containing the updated category product's details
     * @return the updated CategoryProduct object
     */
    CategoryProduct update(long categoryProductId, CategoryProductRequest categoryProductRequest);

    /**
     * Deletes an existing category product based on the provided category product ID.
     *
     * @param id the ID of the category product to delete
     */
    void deleteById(final long id);

    /**
     * Finds an existing category product based on the provided category product ID.
     *
     * @param categoryId the ID of the category product to find
     * @return the CategoryProduct object with the provided ID
     */
    CategoryProduct findById(long categoryId);

}
