package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.ProductRating;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.ProductRatingNotExistException;
import com.black.mulberry.core.mapper.ProductRatingMapper;
import com.black.mulberry.core.repository.ProductRatingRepository;
import com.black.mulberry.core.service.ProductRatingService;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.data.transfer.request.ProductRatingRequest;
import com.black.mulberry.data.transfer.response.ProductRatingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRatingServiceImpl implements ProductRatingService {

    private final ProductRatingRepository productRatingRepository;
    private final ProductRatingMapper productRatingMapper;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public List<ProductRatingResponse> findPaginatedByUserId(long userId, Pageable pageable) {
        log.info("Find Paginated list Product rating by user id: {}", userId);
        userService.findById(userId);
        List<ProductRating> ratingList = productRatingRepository.findAllByUserId(userId, pageable);
        return ratingList.stream()
                .map(productRatingMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public long countAllByUserId(long userId) {
        log.info("Count all Product rating by user id: {}", userId);
        userService.findById(userId);
        return productRatingRepository.countAllByUserId(userId);
    }

    @Override
    public List<ProductRatingResponse> findPaginatedByProductId(long productId, Pageable pageable) {
        log.info("Find Paginated list Product rating by product id: {}", productId);
        productService.findById(productId);
        List<ProductRating> ratingList = productRatingRepository.findAllByProductId(productId, pageable);
        return ratingList.stream()
                .map(productRatingMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public long countAllByProductId(long productId) {
        log.info("Count all Product rating by productId id: {}", productId);
        productService.findById(productId);
        return productRatingRepository.countAllByProductId(productId);
    }

    @Override
    public List<ProductRatingResponse> findPaginated(Pageable pageable) {
        log.info("Find all Paginated list Product rating");
        List<ProductRating> ratingList = productRatingRepository.findAll(pageable).getContent();
        return ratingList.stream()
                .map(productRatingMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public long countAll() {
        log.info("Count all Product rating");
        return productRatingRepository.count();
    }

    @Override
    public int avgProduct(long productId) {
        log.info("Average product Rating by id: {}", productId);
        productService.findById(productId);
        return productRatingRepository.avgProduct(productId);
    }

    @Override
    public ProductRatingResponse rate(ProductRatingRequest productRatingRequest, long userId) {
        final long productId = productRatingRequest.getProductId();
        log.info("Request rate product user id: {} under product id: {}", userId, productId);
        User user = userService.findById(userId);
        Product product = productService.findById(productId);
        Optional<ProductRating> ratingOptional = productRatingRepository.findByUserIdAndProductId(userId, productId);
        ProductRating productRating = productRatingMapper.toEntity(productRatingRequest);

        if (ratingOptional.isEmpty()) {
            productRating.setUser(user);
            productRating.setProduct(product);
        }
        ProductRating save = productRatingRepository.save(productRating);
        log.info("Successfully rated with user: {} under product id: {}", user.getEmail(), productId);
        return productRatingMapper.toResponse(save);
    }

    @Override
    public long canselRating(long userId, long productId) {
        log.info("Request cansel rating with user id: {} under product id: {}", userId, productId);
        userService.findById(userId);
        productService.findById(productId);
        long ratingId = findByUserIdAndProductId(userId, productId).getId();
        productRatingRepository.deleteById(ratingId);
        log.info("Successfully canceled rating with user id: {} under product id: {}", userId, productId);
        return ratingId;
    }

    private ProductRating findByUserIdAndProductId(long userId, long productId) {
        log.info("Find Product rating by user id: {} and product id: {}", userId, productId);
        return productRatingRepository.findByUserIdAndProductId(userId, productId).orElseThrow(() -> {
            log.error("Product rating by user id: {} and product id: {} NOT FOUND", userId, productId);
            return new ProductRatingNotExistException("Product rating with user id: " + userId + " and product id: " + productId + " NOT FOUND");
        });
    }
}
