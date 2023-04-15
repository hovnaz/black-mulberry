package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductBasket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface represents the repository for ProductBasket entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface ProductBasketRepository extends JpaRepository<ProductBasket, Long> {

    /**
     * Finds a ProductBasket entity for a user with the specified user ID, that is currently active.
     *
     * @param userId the ID of the user to search for
     * @return an Optional containing the ProductBasket entity for the user with the specified user ID, that is currently active, if it exists, otherwise an empty Optional
     */
    Optional<ProductBasket> findByUserIdAndIsActualTrue(long userId);

    /**
     * Finds a ProductBasket entity with the specified basket ID and user ID.
     *
     * @param basketId the ID of the basket to search for
     * @param userId the ID of the user to search for
     * @return an Optional containing the ProductBasket entity with the specified basket ID and user ID, if it exists, otherwise an empty Optional
     */
    Optional<ProductBasket> findByIdAndUserId(long basketId, long userId);

}
