package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.ProductComment;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.ProductCommentNotExistException;
import com.black.mulberry.core.mapper.ProductCommentMapper;
import com.black.mulberry.core.repository.ProductCommentRepository;
import com.black.mulberry.core.service.ProductCommentService;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.data.transfer.request.ProductCommentRequest;
import com.black.mulberry.data.transfer.response.ProductCommentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCommentServiceImpl implements ProductCommentService {

    private final UserService userService;
    private final ProductService productService;
    private final ProductCommentRepository productCommentRepository;
    private final ProductCommentMapper productCommentMapper;

    @Override
    public List<ProductCommentResponse> findPaginatedByUserId(long userId, Pageable pageable) {
        userService.findById(userId);
        List<ProductComment> commentList = productCommentRepository.findAllByUserIdAndIsDeleteFalse(userId, pageable);
        return commentList.stream()
                .map(productCommentMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public long countAllByUserId(long userId) {
        userService.findById(userId);
        return productCommentRepository.countAllByUserIdAndIsDeleteFalse(userId);
    }

    @Override
    public List<ProductCommentResponse> findPaginatedByProductIdAndUserId(long productId, long userId, Pageable pageable) {
        productService.findById(productId);
        userService.findById(userId);
        List<ProductComment> commentList = productCommentRepository.findAllByProductIdAndUserIdAndIsDeleteFalse(productId, userId, pageable);
        return commentList.stream()
                .map(productCommentMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public long countAllByProductIdAndUserId(long productId, long userId) {
        productService.findById(productId);
        userService.findById(userId);
        return productCommentRepository.countAllByProductIdAndUserIdAndIsDeleteFalse(productId, userId);
    }

    @Override
    public List<ProductCommentResponse> findPaginatedByProductId(long productId, Pageable pageable) {
        log.info("find count all by product id");
        productService.findById(productId);
        List<ProductComment> commentList = productCommentRepository.findAllByProductIdAndIsDeleteFalse(productId, pageable);
        return commentList.stream()
                .map(productCommentMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public long countAllByProductId(long productId) {
        log.info("get count all by product id: {}", productId);
        productService.findById(productId);
        return productCommentRepository.countAllByProductIdAndIsDeleteFalse(productId);
    }

    @Override
    public ProductComment findById(long id) {
        log.info("Find by product_comment id: {}", id);
        return productCommentRepository.findByIdAndIsDeleteFalse(id).orElseThrow(() -> {
            log.error("Product comment with id: {} not found", id);
            throw new ProductCommentNotExistException("Comment with id: " + id + " NOT FOUND");
        });
    }

    @Override
    public ProductComment findByIdAndUserId(long commentId, long userId) {
        log.info("Find by product comment id: {} and user id: {}", commentId, userId);
        return productCommentRepository.findByIdAndUserIdAndIsDeleteFalse(commentId, userId).orElseThrow(() -> {
            log.error("Product comment with id: {} and user id: {} not found", commentId, userId);
            throw new ProductCommentNotExistException("Product comment with id: " + commentId + " and user id: " + userId + " not found");
        });
    }

    @Override
    public ProductCommentResponse add(ProductCommentRequest productCommentRequest, long userId) {
        final long productId = productCommentRequest.getProductId();
        log.info("Request add product for product id: {} and by user id: {}", productId, userId);
        Product product = productService.findById(productId);
        User user = userService.findById(userId);
        ProductComment productComment = productCommentMapper.toEntity(productCommentRequest);
        productComment.setProduct(product);
        productComment.setUser(user);
        productComment.setIsDelete(false);
        ProductComment save = productCommentRepository.save(productComment);
        log.info("Successfully add comment for product id: {} and by user id: {}", productId, userId);
        return productCommentMapper.toResponse(save);
    }

    @Override
    public ProductCommentResponse deleteById(long commentId, long userId) {
        ProductComment comment = findByIdAndUserId(commentId, userId);
        log.info("request to delete product comment with id: {} by user id: {}", commentId, userId);
        comment.setIsDelete(true);
        ProductComment save = productCommentRepository.save(comment);
        log.info("Product comment with id: {} successfully  deleted", commentId);
        return productCommentMapper.toResponse(save);
    }
}
