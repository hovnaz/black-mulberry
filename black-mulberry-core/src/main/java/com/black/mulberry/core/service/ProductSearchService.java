package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.data.transfer.request.ProductFilterRequest;
import com.black.mulberry.data.transfer.request.ProductSearchRequest;

import java.util.List;

/**
 * The ProductSearchService interface provides methods to search for and filter products.
 */
public interface ProductSearchService {

    /**
     * Searches for products based on the given search criteria.
     *
     * @param productSearchRequest the search criteria to use.
     * @return a list of products that match the search criteria.
     */
    List<Product> searchForProduct(ProductSearchRequest productSearchRequest);

    /**
     * Filters products based on the given price range.
     *
     * @param productFilterRequest the price range to use for filtering.
     * @return a list of products that fall within the specified price range.
     */
    List<Product> filterProductByPrice(ProductFilterRequest productFilterRequest);
}
