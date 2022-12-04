package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.ProductBasket;
import com.black.mulberry.data.transfer.request.ProductBasketItemRequest;
import com.black.mulberry.data.transfer.request.ProductBasketRequest;
import com.black.mulberry.data.transfer.response.ProductBasketItemResponse;
import com.black.mulberry.data.transfer.response.ProductBasketResponse;

import java.math.BigDecimal;

public interface ProductBasketService {

    ProductBasketItemResponse add(ProductBasketItemRequest productBasketItemRequest, long userId);// +

    ProductBasket findByIdAndUserId(long basketId, long userId);// +

    ProductBasketResponse cancelByProductId(long basketId, long userId);// +

    ProductBasket findById(long id);// +

    Long countAllByUserId(Long userId);// +
    void clear(long userId);

    ProductBasketResponse update(ProductBasketRequest productBasketRequest, int quantity, long userId);

    BigDecimal amount();

    BigDecimal amountByProductId(Long productId);

    ProductBasket findActualBasketOrCreate(long userId);

}
