package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.ProductComment;
import com.black.mulberry.core.exception.ProductCommentNotExistException;
import com.black.mulberry.data.transfer.request.ProductCommentRequest;
import com.black.mulberry.data.transfer.response.ProductCommentResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCommentService {

    /**
     * find all comments
     *
     * @param userId   by userId
     * @param pageable pagination
     * @return List<ProductCommentResponse>
     * @see Pageable used for pagination
     */
    List<ProductCommentResponse> findPaginatedByUserId(long userId, Pageable pageable);

    long countAllByUserId(long userId);

    /**
     * find all comments
     *
     * @param userId    by userId
     * @param productId by productId
     * @param pageable  pagination
     * @return List<ProductCommentResponse>
     * @see Pageable used for pagination
     */
    List<ProductCommentResponse> findPaginatedByProductIdAndUserId(long productId, long userId, Pageable pageable);

    long countAllByProductIdAndUserId(long productId, long userId);

    /**
     * find all comments
     *
     * @param productId by productId
     * @param pageable  pagination
     * @return List<ProductCommentResponse>
     * @see Pageable used for pagination
     */
    List<ProductCommentResponse> findPaginatedByProductId(long productId, Pageable pageable);

    long countAllByProductId(long productId);

    /**
     * find comment
     *
     * @param id comment id
     * @return ProductComment
     * @throws ProductCommentNotExistException if not found
     */
    ProductComment findById(long id);

    /**
     * find comment
     *
     * @param commentId by comment id
     * @param userId    by user id
     * @return ProductComment
     * @throws ProductCommentNotExistException if not found
     */
    ProductComment findByIdAndUserId(long commentId, long userId);

    /**
     * add comment
     *
     * @param productCommentRequest DTO request
     * @param userId                by user id
     * @return ProductCommentResponse
     */
    ProductCommentResponse add(ProductCommentRequest productCommentRequest, long userId);

    /**
     * delete comment
     * make comment status isDelete to true
     *
     * @param commentId by comment id
     * @param userId    by user id
     * @return ProductCommentResponse
     */
    ProductCommentResponse deleteById(long commentId, long userId);
}
