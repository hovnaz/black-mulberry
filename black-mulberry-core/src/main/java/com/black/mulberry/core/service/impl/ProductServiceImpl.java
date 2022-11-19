package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.ProductNotExistException;
import com.black.mulberry.core.mapper.ProductMapper;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.core.util.IOUtil;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryProductImpl categoryProductService;
    private final ProductMapper productMapper;
    private final IOUtil ioUtil;

    @Value("blackMulberry.product.images")
    private String folderPath;

    @Override
    public Product save(ProductRequest productRequest, User user) {
        log.info("request from user: {} to create product", user.getEmail());
        User userById = userService.findById(user.getId());
        CategoryProduct categoryById = categoryProductService.findById(productRequest.getCategoryProductId());
        Product product = productMapper.toEntity(productRequest);
        product.setCategoryProduct(categoryById);
        product.setUser(userById);
        Product save = productRepository.save(product);
        log.info("product with id: {} succesfully created", save.getId());
        return save;
    }

    @Override
    public Product findById(long id) {
        log.info("Request to get product with id: {}", id);
        Product product = productRepository.findByIdAndIsDeleteFalse(id).orElseThrow(() -> {
            log.error("product with id: {} not found", id);
            throw new ProductNotExistException("product with id: " + id + " does not exist");
        });
        log.info("succesfully found product with id: {}", id);
        return product;
    }

    @Override
    public Product findByIdAndUserId(long productId, long userId) {
        log.info("request to get product with id: {}", productId);
        Product product = productRepository.findByIdAndIsDeleteFalseAndUserId(productId, userId).orElseThrow(() -> {
            log.error("product with id: {} not found", productId);
            throw new ProductNotExistException("product with id: " + productId + " does not exist");
        });
        log.info("succesfully found product with id: {}", productId);
        return product;
    }

    @Override
    public List<ProductResponse> findAll() {
        log.info("Find all Product list");
        List<Product> productList = productRepository.findAllByIsDeleteFalse();
        return productList.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<ProductResponse> findAllByUserId(long userId) {
        log.info("Find all Product list by user id: {}", userId);
        List<Product> productList = productRepository.findAllByIsDeleteFalseAndUserId(userId);
        return productList.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Product update(long productId, long userId, ProductRequest productRequest) {
        log.info("request to update product with id: {} and by UserId {}", productId, userId);
        Product product = productMapper.toEntity(productRequest);
        Product productById = findByIdAndUserId(productId, userId);
        CategoryProduct categoryById = categoryProductService.findById(productRequest.getCategoryProductId());
        product.setId(productById.getId());
        product.setCategoryProduct(categoryById);
        product.setUser(productById.getUser());
        Product save = productRepository.save(product);
        log.info("product with id: {} successfully updated", productId);
        return save;
    }

    @Override
    public void deleteById(long productId, long userId) {
        Product productById = findByIdAndUserId(productId, userId);
        log.info("request from user {} to delete product with id: {}", productById.getUser().getName(), productId);
        productById.setDelete(true);
        productRepository.save(productById);
        log.info("product with id: {} successfully  deleted", productId);
    }

    @Override
    public byte[] getImage(String fileName) throws IOException {
        return ioUtil.getAllBytesByUrl(folderPath + File.separator + fileName);

    }
}
