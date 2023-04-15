package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductRating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the repository for ProductRating entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

    /**
     * Finds all ProductRating entities for a user with the specified user ID, with pagination.
     *
     * @param userId   the ID of the user to search for
     * @param pageable the pagination criteria
     * @return a List containing all ProductRating entities for the specified user, with pagination
     * @see Pageable
     */
    List<ProductRating> findAllByUserId(long userId, Pageable pageable);

    /**
     * Counts all ProductRating entities for a user with the specified user ID.
     *
     * @param userId the ID of the user to search for
     * @return the total number of ProductRating entities for the specified user
     */
    long countAllByUserId(long userId);

    /**
     * Finds all ProductRating entities for a product with the specified product ID, with pagination.
     *
     * @param productId the ID of the product to search for
     * @param pageable  the pagination criteria
     * @return a List containing all ProductRating entities for the specified product, with pagination
     * @see Pageable
     */
    List<ProductRating> findAllByProductId(long productId, Pageable pageable);

    /**
     * Counts all ProductRating entities for a product with the specified product ID.
     *
     * @param productId the ID of the product to search for
     * @return the total number of ProductRating entities for the specified product
     */
    long countAllByProductId(long productId);

    /**
     * Finds a ProductRating entity with the specified user ID and product ID.
     *
     * @param userId    the ID of the user to search for
     * @param productId the ID of the product to search for
     * @return an Optional containing the ProductRating entity with the specified user ID and product ID, or an empty Optional if no matching entity is found
     */
    Optional<ProductRating> findByUserIdAndProductId(long userId, long productId);

    /**
     * Calculates the average rating for a product with the specified product ID.
     *
     * @param productId the ID of the product to calculate the average rating for
     * @return the average rating for the specified product, or 0 if no rating exists for the product
     */
    @Query(value = "select coalesce(AVG(rating), 0) FROM product_rating where product_id = :productId", nativeQuery = true)
    Integer avgProduct(@Param("productId") long productId);
}
