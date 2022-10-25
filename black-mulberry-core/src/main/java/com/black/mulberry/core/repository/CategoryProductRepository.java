package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {
}
