package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.ProductNotFoundException;
import com.black.mulberry.core.mapper.CategoryProductMapper;
import com.black.mulberry.core.mapper.ProductMapper;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.service.CategoryProductService;
import com.black.mulberry.core.service.ProductPriceHistoryService;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.core.util.IOUtil;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductPriceHistoryService productPriceHistoryService;
    private final ProductRepository productRepository;
    @PersistenceContext
    EntityManager entityManager;
    private final UserService userService;
    private final CategoryProductService categoryProductService;
    private final ProductMapper productMapper;
    private final CategoryProductMapper categoryProductMapper;
    private final IOUtil ioUtil;
    @Value("${blackMulberry.images.product}")
    private String folderPath;

    @Override
    public Product save(ProductRequest productRequest, long userId) {
        log.info("request from user id: {} to create product", userId);
        User userById = userService.findById(userId);
        CategoryProduct categoryById = categoryProductService.findById(productRequest.getCategoryProductId());
        Product product = productMapper.toEntity(productRequest);
        product.setCategoryProduct(categoryById);
        product.setUser(userById);
        Product save = productRepository.save(product);
        productPriceHistoryService.add(product);
        log.info("product with id: {} successfully created", save.getId());
        return save;
    }

    @Override
    public Product findById(long id) {
        log.info("Request to get product with id: {}", id);
        Product product = productRepository.findByIdAndIsDeleteFalse(id).orElseThrow(() -> {
            log.error("product with id: {} not found", id);
            throw new ProductNotFoundException("product with id: " + id + " does not exist");
        });
        log.info("successfully found product with id: {}", id);
        return product;
    }

    @Override
    public Product findByIdAndUserId(long productId, long userId) {
        log.info("request to get product with id: {}", productId);
        Product product = productRepository.findByIdAndUserIdAndIsDeleteFalse(productId, userId).orElseThrow(() -> {
            log.error("product with id: {} not found", productId);
            throw new ProductNotFoundException("product with id: " + productId + " does not exist");
        });
        log.info("successfully found product with id: {}", productId);
        return product;
    }

    @Override
    public List<ProductResponse> findAll(Pageable pageable) {
        log.info("Find all Product list");
        List<Product> productList = productRepository.findAllByIsDeleteFalse(pageable);
        return productList.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<ProductResponse> findAllByUserId(long userId, Pageable pageable) {
        log.info("Find all Product list by user id: {}", userId);
        List<Product> productList = productRepository.findAllByUserIdAndIsDeleteFalse(userId, pageable);
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
        productPriceHistoryService.add(product);
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
    public byte[] getImage(String fileName) {
        return ioUtil.getAllBytesByUrl(folderPath + File.separator + fileName);
    }

    @Override
    public String saveImage(MultipartFile file) {
        return ioUtil.saveImage(folderPath + File.separator, file);
    }

    @Override
    public long countAll() {
        return productRepository.countAllByIsDeleteFalse();
    }

    @Override
    public long countAllByCategoryId(long categoryId) {
        return productRepository.countAllByCategoryProductIdAndIsDeleteFalse(categoryId);
    }

    @Override
    public long countAllByUserId(long userId) {
        userService.findById(userId);
        return productRepository.countAllByUserIdAndIsDeleteFalse(userId);
    }

    @Override
    public List<ProductResponse> findAllByCategoryProduct(long categoryProductId, Pageable pageable) {
        List<Product> productByCategory = productRepository.findAllByCategoryProductIdAndIsDeleteFalse(categoryProductId, pageable);
        return productByCategory.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
