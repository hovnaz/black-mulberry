package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.ProductComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
    /**
     * find all product comments
     * used pageable
     * when comment is not deleted
     *
     * @param userId
     * @param pageable
     * @return List<ProductComment>
     * @see Pageable
     */
    List<ProductComment> findAllByUserIdAndIsDeleteFalse(long userId, Pageable pageable);

    /**
     * count all product comments
     * used pageable
     * when comment is not deleted
     *
     * @param userId by userId
     * @return List<ProductComment>
     */
    long countAllByUserIdAndIsDeleteFalse(long userId);

    /**
     * find all product comments
     * by productId and userId
     * used pageable
     * when comment is not deleted
     *
     * @param productId
     * @param userId
     * @param pageable
     * @return List<ProductComment>
     * @see Pageable
     */
    List<ProductComment> findAllByProductIdAndUserIdAndIsDeleteFalse(long productId, long userId, Pageable pageable);

    /**
     * count all product comments
     * by productId and userId
     * when comment is not deleted
     *
     * @param productId
     * @param userId
     * @return List<ProductComment>
     */
    long countAllByProductIdAndUserIdAndIsDeleteFalse(long productId, long userId);

    /**
     * find all product comments
     * by productId
     * used pageable
     * when comment is not deleted
     *
     * @param productId
     * @param pageable
     * @return List<ProductComment>
     * @see Pageable
     */
    List<ProductComment> findAllByProductIdAndIsDeleteFalse(long productId, Pageable pageable);

    /**
     * count all product comments
     * when comment is not deleted
     *
     * @param productId
     * @return List<ProductComment>
     */
    long countAllByProductIdAndIsDeleteFalse(long productId);

    /**
     * find comment
     * when comment is not deleted
     *
     * @param id
     * @return List<ProductComment>
     */
    Optional<ProductComment> findByIdAndIsDeleteFalse(long id);

    /**
     * find comment
     * when comment is not deleted
     *
     * @param userId
     * @param commentId
     * @return List<ProductComment>
     */
    Optional<ProductComment> findByIdAndUserIdAndIsDeleteFalse(long commentId, long userId);
}
