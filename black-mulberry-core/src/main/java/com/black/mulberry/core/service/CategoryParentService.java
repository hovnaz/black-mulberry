package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.CategoryParent;
import com.black.mulberry.data.transfer.request.CategoryParentRequest;
import com.black.mulberry.data.transfer.response.CategoryParentResponse;

import java.util.List;

/**
 * A service interface for managing category parents.
 */
public interface CategoryParentService {

    /**
     * Saves a new category parent based on the provided category parent request.
     *
     * @param categoryParentRequest the category parent request containing the category parent's details
     * @return the newly saved CategoryParent object
     */
    CategoryParent save(CategoryParentRequest categoryParentRequest);

    /**
     * Returns a list of all existing category parents.
     *
     * @return a list of CategoryParentResponse objects representing all existing category parents
     */
    List<CategoryParentResponse> findAll();

    /**
     * Updates an existing category parent based on the provided category parent ID and category parent request.
     *
     * @param categoryParentId the ID of the category parent to update
     * @param categoryParentRequest the category parent request containing the updated category parent's details
     * @return the updated CategoryParent object
     */
    CategoryParent update(long categoryParentId, CategoryParentRequest categoryParentRequest);

    /**
     * Deletes an existing category parent based on the provided category parent ID.
     *
     * @param id the ID of the category parent to delete
     */
    void deleteById(final long id);

    /**
     * Finds an existing category parent based on the provided category parent ID.
     *
     * @param categoryParentId the ID of the category parent to find
     * @return the CategoryParent object with the provided ID
     */
    CategoryParent findById(long categoryParentId);

}
