package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
