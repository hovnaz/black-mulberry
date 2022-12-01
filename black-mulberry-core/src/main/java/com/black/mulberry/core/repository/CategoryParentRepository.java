package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.CategoryParent;
import com.black.mulberry.core.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryParentRepository extends JpaRepository<CategoryParent, Long> {
}
