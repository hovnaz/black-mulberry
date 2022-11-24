package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductRating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

    /**
     * find all product ratings only to the user
     *
     * @param userId   for find by user id
     * @param pageable for pagination
     * @return List<ProductRating>
     * @see Pageable
     */
    List<ProductRating> findAllByUserId(long userId, Pageable pageable);

    /**
     * count all rating only to the user
     *
     * @param userId for find by user id
     * @return long
     */
    long countAllByUserId(long userId);

    /**
     * find all product ratings only to the product
     *
     * @param productId for find by product id
     * @param pageable  for pagination
     * @return List<ProductRating>
     * @see Pageable
     */
    List<ProductRating> findAllByProductId(long productId, Pageable pageable);

    /**
     * count all rating only to the product
     *
     * @param productId for find by user id
     * @return long
     */
    long countAllByProductId(long productId);

    /**
     * find rating by user id and product id
     *
     * @param userId    for find by user id
     * @param productId for find product id
     * @return Optional<ProductRating>
     */
    Optional<ProductRating> findByUserIdAndProductId(long userId, long productId);

    /**
     * rating average sum for product
     *
     * @param productId for find by id
     * @return int
     */
    @Query(value = "select AVG(rating) FROM product_rating where product_id = :productId", nativeQuery = true)
    int avgProduct(@Param("productId") long productId);
}
