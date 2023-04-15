package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the repository for ProductComment entities,
 * extending the JpaRepository interface from Spring Data JPA.
 */
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

    /**
     * Finds all ProductComment entities for a user with the specified user ID, with pagination,
     * when the comment is not deleted.
     *
     * @param userId   the ID of the user to search for
     * @param pageable pagination information
     * @return a List of all ProductComment entities for the user with the specified user ID, with the specified pagination, when the comment is not deleted
     */
    List<ProductComment> findAllByUserIdAndIsDeleteFalse(long userId, Pageable pageable);

    /**
     * Counts the number of ProductComment entities for a user with the specified user ID,
     * when the comment is not deleted.
     *
     * @param userId the ID of the user to search for
     * @return the total number of ProductComment entities for the user with the specified user ID, when the comment is not deleted
     */
    long countAllByUserIdAndIsDeleteFalse(long userId);

    /**
     * Finds all ProductComment entities for a product with the specified product ID and user with the specified user ID,
     * with pagination, when the comment is not deleted.
     *
     * @param productId the ID of the product to search for
     * @param userId    the ID of the user to search for
     * @param pageable  pagination information
     * @return a List of all ProductComment entities for the product with the specified product ID and user with the specified user ID, with the specified pagination, when the comment is not deleted
     */
    List<ProductComment> findAllByProductIdAndUserIdAndIsDeleteFalse(long productId, long userId, Pageable pageable);

    /**
     * Counts the number of ProductComment entities for a product with the specified product ID and user with the specified user ID,
     * when the comment is not deleted.
     *
     * @param productId the ID of the product to search for
     * @param userId    the ID of the user to search for
     * @return the total number of ProductComment entities for the product with the specified product ID and user with the specified user ID, when the comment is not deleted
     */
    long countAllByProductIdAndUserIdAndIsDeleteFalse(long productId, long userId);

    /**
     * Finds all ProductComment entities for a product with the specified product ID,
     * with pagination, when the comment is not deleted.
     *
     * @param productId the ID of the product to search for
     * @param pageable  pagination information
     * @return a List of all ProductComment entities for the product with the specified product ID, with the specified pagination, when the comment is not deleted
     */
    List<ProductComment> findAllByProductIdAndIsDeleteFalse(long productId, Pageable pageable);

    /**
     * Counts the number of ProductComment entities for a product with the specified product ID,
     * when the comment is not deleted.
     *
     * @param productId the ID of the product to search for
     * @return the total number of ProductComment entities for the product with the specified product ID, when the comment is not deleted
     */
    long countAllByProductIdAndIsDeleteFalse(long productId);

    /**
     * Finds a ProductComment entity with the specified ID,
     * when the comment is not deleted
     *
     * @param id the ID of the comment to search for
     * @return an Optional containing the ProductComment entity with the specified ID, when the comment is not deleted, otherwise an empty Optional
     */
    Optional<ProductComment> findByIdAndIsDeleteFalse(long id);

    /**
     * Finds a ProductComment entity with the specified ID and user ID,
     * when the comment is not deleted.
     *
     * @param commentId the ID of the comment to search for
     * @param userId    the ID of the user to search for
     * @return an Optional containing the ProductComment entity with the specified ID and user ID, when the comment is not deleted, otherwise an empty Optional
     */
    Optional<ProductComment> findByIdAndUserIdAndIsDeleteFalse(long commentId, long userId);
}
