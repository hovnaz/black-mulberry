package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.ProductPriceHistory;
import com.black.mulberry.core.repository.ProductPriceHistoryRepository;
import com.black.mulberry.core.service.ProductPriceHistoryService;
import com.black.mulberry.core.support.ProductSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductPriceHistoryServiceImpl implements ProductPriceHistoryService {

    private final ProductSupport productServiceSupport;
    private final ProductPriceHistoryRepository productPriceHistoryRepository;

    @Override
    public void add(Product product) {
        productServiceSupport.ifPresentOrElseThrow(product);
        ProductPriceHistory productPriceHistory = ProductPriceHistory.builder()
                .product(product)
                .price(product.getPrice())
                .build();
        productPriceHistoryRepository.save(productPriceHistory);
    }
}
