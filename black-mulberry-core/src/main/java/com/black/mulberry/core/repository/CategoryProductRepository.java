package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents the repository for CategoryProduct entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {

    /**
     * Counts the number of CategoryProduct entities with the specified categoryParentId and isDeleteFalse.
     *
     * @param categoryParentId the id of the parent category to search for
     * @return the total number of CategoryProduct entities with the specified categoryParentId and isDeleteFalse
     */
    int countAllByCategoryParentIdAndIsDeleteFalse(long categoryParentId);
}
