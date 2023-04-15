package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the repository for Product entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds all Product entities that are not deleted, with pagination.
     *
     * @param pageable the pagination criteria
     * @return a List containing all non-deleted Product entities, with pagination
     * @see Pageable
     */
    List<Product> findAllByIsDeleteFalse(Pageable pageable);

    /**
     * Finds all Product entities for a user with the specified user ID that are not deleted, with pagination.
     *
     * @param userId the ID of the user to search for
     * @param pageable the pagination criteria
     * @return a List containing all non-deleted Product entities for the specified user, with pagination
     * @see Pageable
     */
    List<Product> findAllByUserIdAndIsDeleteFalse(long userId, Pageable pageable);

    /**
     * Finds a Product entity with the specified ID that is not deleted.
     *
     * @param id the ID of the Product entity to search for
     * @return an Optional containing the non-deleted Product entity with the specified ID, or an empty Optional if no matching entity is found
     */
    Optional<Product> findByIdAndIsDeleteFalse(long id);

    /**
     * Finds a Product entity with the specified ID and user ID that is not deleted.
     *
     * @param productId the ID of the Product entity to search for
     * @param userId the ID of the user to search for
     * @return an Optional containing the non-deleted Product entity with the specified ID and user ID, or an empty Optional if no matching entity is found
     */
    Optional<Product> findByIdAndUserIdAndIsDeleteFalse(long productId, long userId);

    /**
     * Counts all non-deleted Product entities for a user with the specified user ID.
     *
     * @param userId the ID of the user to search for
     * @return the total number of non-deleted Product entities for the specified user
     */
    long countAllByUserIdAndIsDeleteFalse(long userId);

    /**
     * Counts all non-deleted Product entities.
     *
     * @return the total number of non-deleted Product entities
     */
    long countAllByIsDeleteFalse();

    /**
     * Counts the number of Products with the specified CategoryProduct ID that are not deleted.
     *
     * @param categoryProductId the ID of the CategoryProduct to search for
     * @return the total number of Products with the specified CategoryProduct ID that are not deleted
     */
    int countAllByCategoryProductIdAndIsDeleteFalse(long categoryProductId);

    /**
     * Finds all Products with the specified CategoryProduct ID that are not deleted, with pagination.
     *
     * @param categoryProductsId the ID of the CategoryProduct to search for
     * @param pageable the pagination criteria
     * @return a List containing all non-deleted Products with the specified CategoryProduct ID, with pagination
     * @see Pageable
     */
    List<Product> findAllByCategoryProductIdAndIsDeleteFalse(long categoryProductsId, Pageable pageable);
}
