package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {

    /**
     * @param categoryParentId for find by id
     * @return counted sum all by parent category id
     */
    int countAllByCategoryParentIdAndIsDeleteFalse(long categoryParentId);
}
