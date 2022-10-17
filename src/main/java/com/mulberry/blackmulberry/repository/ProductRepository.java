package com.mulberry.blackmulberry.repository;

import com.mulberry.blackmulberry.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Integer> {
}
