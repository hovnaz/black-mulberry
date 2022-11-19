package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * find all products
     * when product is not deleted
     *
     * @return List<Product>
     */
    List<Product> findAllByIsDeleteFalse();

    /**
     * find all products by userId
     * when product is not deleted
     *
     * @param userId
     * @return List<Product>
     */
    List<Product> findAllByIsDeleteFalseAndUserId(long userId);

    /**
     * find all product by id
     * when product is not deleted
     *
     * @param id
     * @return
     */
    Optional<Product> findByIdAndIsDeleteFalse(long id);

    /**
     * find product by id and user id
     * when product is not deleted
     *
     * @param productId
     * @param userId
     * @return
     */
    Optional<Product> findByIdAndIsDeleteFalseAndUserId(long productId, long userId);
}
