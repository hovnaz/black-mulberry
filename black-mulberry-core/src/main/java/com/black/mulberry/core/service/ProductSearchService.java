package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.data.transfer.request.ProductFilterRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;

import java.util.List;

public interface ProductSearchService {
    List<Product> searchForProduct(ProductFilterRequest productFilterRequest);
    List<Product> filterProductByPrice(ProductFilterRequest productFilterRequest);
}
