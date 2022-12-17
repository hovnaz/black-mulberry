package com.black.mulberry.core.support;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.exception.ProductNotFoundException;
import com.black.mulberry.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductSupport {

    private final ProductRepository productRepository;

    public void ifPresentOrElseThrow(long productId) {
        log.info("Find by product id: {}", productId);
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            log.error("Product with id: {} not found", productId);
            throw new ProductNotFoundException("with id: " + productId + " NOT FOUND");
        }
    }

    public void ifPresentOrElseThrow(Product product) {
        ifPresentOrElseThrow(product.getId());
    }
}
