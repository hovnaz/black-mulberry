package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Product;

/**
 * The ProductPriceHistoryService interface provides a method to keep track of a product's price history.
 */
public interface ProductPriceHistoryService {

    /**
     * trigger every update product price
     * keep all history price product
     *
     * @param product the product to add to the price history.
     * @see com.black.mulberry.core.entity.ProductPriceHistory
     */
    void add(Product product);
}
