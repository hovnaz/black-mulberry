package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIsDeleteFalse();
    Optional<Product> findByIdAndIsDeleteFalse(Long id);
}
