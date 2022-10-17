package com.mulberry.blackmulberry.repository;

import com.mulberry.blackmulberry.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct,Integer> {
}
