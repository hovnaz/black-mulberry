package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Product;

public interface ProductPriceHistoryService {

    /**
     * trigger every update product price
     * keep all history price product
     *
     * @param product
     * @see com.black.mulberry.core.entity.ProductPriceHistory
     */
    void add(Product product);
}
