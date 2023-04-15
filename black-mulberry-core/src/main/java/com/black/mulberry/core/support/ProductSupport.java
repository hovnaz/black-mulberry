package com.black.mulberry.core.support;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.exception.ProductNotFoundException;
import com.black.mulberry.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The ProductSupport class provides methods for checking product-related information.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductSupport {

    private final ProductRepository productRepository;

    /**
     * Checks if a product with a given ID exists, and throws an exception if it doesn't.
     *
     * @param productId the ID of the product to check
     * @throws ProductNotFoundException if a product with the given ID doesn't exist
     */
    public void ifPresentOrElseThrow(long productId) {
        log.info("Finding product by ID: {}", productId);
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            log.error("Product with ID {} not found", productId);
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
    }

    /**
     * Checks if a product exists, and throws an exception if it doesn't.
     *
     * @param product the product to check
     * @throws ProductNotFoundException if the product doesn't exist
     */
    public void ifPresentOrElseThrow(Product product) {
        ifPresentOrElseThrow(product.getId());
    }
}
