package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.Product;
import org.springframework.data.domain.Pageable;
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
    List<Product>  findAllByIsDeleteFalseOrderByCreateAtDesc(Pageable pageable);

    /**
     * find all products by userId
     * when product is not deleted
     *
     * @param userId
     * @return List<Product>
     */
    List<Product> findAllByIsDeleteFalseAndUserIdOrderByCreateAtDesc(long userId, Pageable pageable);

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

    long countAllByIsDeleteFalseAndUserId(long id);

    long countAllByIsDeleteFalse();
}
