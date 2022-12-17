package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.ProductBasket;
import com.black.mulberry.data.transfer.request.ProductBasketItemRequest;
import com.black.mulberry.data.transfer.response.ProductBasketItemResponse;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductBasketService {

    /**
     * add to actual basket product
     * if not found
     *
     * @param productBasketItemRequest if
     * @param userId                   from user id
     * @return added item
     */
    ProductBasketItemResponse add(ProductBasketItemRequest productBasketItemRequest, long userId);

    /**
     * remove the product from the cart
     *
     * @param userId    by id
     * @param productId by id
     */
    void cancelByProductId(long userId, long productId);

    List<ProductBasketItemResponse> findAllByActual(long userId, Pageable pageable);

    ProductBasket findBasketByActual(long userId);

    ProductBasket findByIdAndUserId(long basketId, long userId);

    long countAllByUserId(long userId);

    /**
     * remove the all products from the cart
     *
     * @param userId by id
     */
    void clear(long userId);

    /**
     * update item quantity
     *
     * @param productBasketItemRequest
     * @param userId
     * @return new updated item
     */
    ProductBasketItemResponse update(ProductBasketItemRequest productBasketItemRequest, long userId);

    /**
     * total amount actual basket
     *
     * @param userId by id
     * @return amount all products
     */
    BigDecimal actualAmountByUserId(long userId);

    /**
     * total amount basket by id
     *
     * @param userId
     * @param basketId sum only this basket total amount
     * @return amount all products
     */
    BigDecimal amountByUserIdAndBasketId(long userId, long basketId);
}
