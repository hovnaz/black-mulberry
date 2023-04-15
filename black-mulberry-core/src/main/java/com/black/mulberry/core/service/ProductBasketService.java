package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.ProductBasket;
import com.black.mulberry.data.transfer.request.ProductBasketItemRequest;
import com.black.mulberry.data.transfer.response.ProductBasketItemResponse;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

/**
 * The ProductBasketService interface provides methods to manage product baskets.
 */
public interface ProductBasketService {

    /**
     * Adds a new product to the user's basket or updates its quantity if already present.
     *
     * @param productBasketItemRequest the product to add or update.
     * @param userId                   the id of the user.
     * @return the added or updated item.
     */
    ProductBasketItemResponse add(ProductBasketItemRequest productBasketItemRequest, long userId);

    /**
     * Removes a product from the user's basket.
     *
     * @param userId    the id of the user.
     * @param productId the id of the product to remove.
     */
    void cancelByProductId(long userId, long productId);

    /**
     * Returns a paginated list of all products in the user's actual basket.
     *
     * @param userId   the id of the user.
     * @param pageable the pageable information.
     * @return the list of products.
     */
    List<ProductBasketItemResponse> findAllByActual(long userId, Pageable pageable);

    /**
     * Finds the user's actual basket.
     *
     * @param userId the id of the user.
     * @return the actual basket.
     */
    ProductBasket findBasketByActual(long userId);

    /**
     * Finds a basket by id and user id.
     *
     * @param basketId the id of the basket to find.
     * @param userId   the id of the user.
     * @return the found basket.
     */
    ProductBasket findByIdAndUserId(long basketId, long userId);

    /**
     * Counts the number of products in the user's basket.
     *
     * @param userId the id of the user.
     * @return the number of products.
     */
    long countAllByUserId(long userId);

    /**
     * Removes all products from the user's basket.
     *
     * @param userId the id of the user.
     */
    void clear(long userId);

    /**
     * Updates the quantity of a product in the user's basket.
     *
     * @param productBasketItemRequest the updated product.
     * @param userId                   the id of the user.
     * @return the updated product.
     */
    ProductBasketItemResponse update(ProductBasketItemRequest productBasketItemRequest, long userId);

    /**
     * Calculates the total amount of the user's actual basket.
     *
     * @param userId the id of the user.
     * @return the total amount.
     */
    BigDecimal actualAmountByUserId(long userId);

    /**
     * Calculates the total amount of a specific basket for a user.
     *
     * @param userId   the id of the user.
     * @param basketId the id of the basket.
     * @return the total amount.
     */
    BigDecimal amountByUserIdAndBasketId(long userId, long basketId);
}
