package com.black.mulberry.core.service;

import com.black.mulberry.data.transfer.request.ProductBasketItemRequest;
import com.black.mulberry.data.transfer.response.ProductBasketItemResponse;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductBasketService {

    ProductBasketItemResponse add(ProductBasketItemRequest productBasketItemRequest, long userId);// +

    void cancelByProductId(long userId, long productId);

    List<ProductBasketItemResponse> findAllByActual(long userId, Pageable pageable);

    long countAllByUserId(long userId);// +

    void clear(long userId);

    ProductBasketItemResponse update(ProductBasketItemRequest productBasketItemRequest, long userId);

    BigDecimal amountByUserId(long userId);

}
