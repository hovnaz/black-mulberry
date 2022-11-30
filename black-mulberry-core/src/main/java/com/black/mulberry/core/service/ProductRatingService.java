package com.black.mulberry.core.service;

import com.black.mulberry.data.transfer.request.ProductRatingRequest;
import com.black.mulberry.data.transfer.response.ProductRatingResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRatingService {

    /**
     * find all product ratings only to the user
     *
     * @param userId   for find by user id
     * @param pageable for pagination
     * @return List<ProductRatingResponse>
     * @see Pageable
     */
    List<ProductRatingResponse> findPaginatedByUserId(long userId, Pageable pageable);

    /**
     * count all rating only to the user
     *
     * @param userId for to be isolated from other users' data
     * @return count all rating
     */
    long countAllByUserId(long userId);

    /**
     * find all product ratings only to the product
     *
     * @param productId for find by product id
     * @param pageable  for pagination
     * @return List<ProductRatingResponse>
     * @see Pageable
     */
    List<ProductRatingResponse> findPaginatedByProductId(long productId, Pageable pageable);

    /**
     * count all rating only to the product
     *
     * @param productId for find by user id
     * @return count all rating
     */
    long countAllByProductId(long productId);

    /**
     * find all product ratings
     *
     * @param pageable for pagination
     * @return List<ProductRatingResponse>
     */
    List<ProductRatingResponse> findPaginated(Pageable pageable);

    /**
     * count all rating
     *
     * @return count all rating
     */
    long countAll();

    /**
     * rating average sum for product
     *
     * @param productId for find by id
     * @return product rating average sum
     */
    int avgProduct(long productId);

    /**
     * rate product
     *
     * @param productRatingRequest DTO Request
     * @param userId               for to be isolated from other users' data
     * @return ProductRatingResponse
     */
    ProductRatingResponse rate(ProductRatingRequest productRatingRequest, long userId);

    /**
     * cansel rating under product
     *
     * @param userId    for to be isolated from other users' data
     * @param productId for find by id
     * @return canceled rating id
     */
    long canselRating(long userId, long productId);

    /**
     *
     * @param productId find product id
     * @param userId for to be isolated from other users' data
     * @return product rating by rated user
     */
    int findRateByProductIdUserId(long productId, long userId);
}
