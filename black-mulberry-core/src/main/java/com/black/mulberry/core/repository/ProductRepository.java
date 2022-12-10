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
    List<Product> findAllByIsDeleteFalse(Pageable pageable);

    /**
     * find all products by userId
     * when product is not deleted
     *
     * @param userId
     * @return List<Product>
     */
    List<Product> findAllByUserIdAndIsDeleteFalse(long userId, Pageable pageable);

    /**
     * find all product by id
     * when product is not deleted
     *
     * @param id
     */
    Optional<Product> findByIdAndIsDeleteFalse(long id);

    /**
     * find product by id and user id
     * when product is not deleted
     *
     * @param productId
     * @param userId
     */
    Optional<Product> findByIdAndUserIdAndIsDeleteFalse(long productId, long userId);

    /**
     * this method counts all non deleted products
     * by userId
     * @param userId
     * @return long count
     */
    long countAllByUserIdAndIsDeleteFalse(long userId);

    /**
     * this method counts all non deleted products
     * @return long count
     */
    long countAllByIsDeleteFalse();

    int countAllByCategoryProductIdAndIsDeleteFalse(long categoryProductId);

    List<Product> findAllByCategoryProductIdAndIsDeleteFalse(long categoryProductsId, Pageable pageable);
}
