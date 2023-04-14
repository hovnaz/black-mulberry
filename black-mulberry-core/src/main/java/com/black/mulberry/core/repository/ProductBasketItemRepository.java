package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductBasketItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the repository for ProductBasketItem entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface ProductBasketItemRepository extends JpaRepository<ProductBasketItem, Long> {

    /**
     * Finds a ProductBasketItem entity with the specified basket ID and product ID.
     *
     * @param basketId  the ID of the basket to search for
     * @param productId the ID of the product to search for
     * @return an Optional containing the ProductBasketItem entity with the specified basket ID and product ID, if it exists, otherwise an empty Optional
     */
    Optional<ProductBasketItem> findByProductBasketIdAndProductId(long basketId, long productId);

    /**
     * Finds all ProductBasketItem entities for a basket with the specified basket ID, with pagination.
     *
     * @param basketId the ID of the basket to search for
     * @param pageable pagination information
     * @return a List of all ProductBasketItem entities for the basket with the specified basket ID, with the specified pagination
     */
    List<ProductBasketItem> findAllByProductBasketId(long basketId, Pageable pageable);

    /**
     * Finds all ProductBasketItem entities for a basket with the specified basket ID.
     *
     * @param basketId the ID of the basket to search for
     * @return a List of all ProductBasketItem entities for the basket with the specified basket ID
     */
    List<ProductBasketItem> findAllByProductBasketId(long basketId);

    /**
     * Counts the number of ProductBasketItem entities for a basket with the specified basket ID.
     *
     * @param basketId the ID of the basket to search for
     * @return the total number of ProductBasketItem entities for the basket with the specified basket ID
     */
    long countAllByProductBasketId(long basketId);

    /**
     * Deletes all ProductBasketItem entities for a basket with the specified basket ID.
     *
     * @param basketId the ID of the basket to delete ProductBasketItem entities for
     */
    void deleteAllByProductBasketId(long basketId);
}
