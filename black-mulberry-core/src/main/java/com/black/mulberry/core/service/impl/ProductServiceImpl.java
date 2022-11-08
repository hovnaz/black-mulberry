package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.ProductNotExistException;
import com.black.mulberry.core.mapper.ProductMapper;
import com.black.mulberry.core.repository.CategoryProductRepository;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryProductRepository categoryProductRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @Value("blackMulberry.product.images")
    private String folderPath;

    @Override
    public Product save(ProductRequest productRequest,User user) {
        log.info("request from user: {} to create product",user.getName());
        Optional<User> optionalUser = userRepository.findById(user.getId());
        Optional<CategoryProduct> categoryProduct = categoryProductRepository.findById(productRequest.getCategoryProductId());
        if(categoryProduct.isEmpty()){
            log.debug("category can't be null");
            throw new NullPointerException("category is null");
        }
        Product product = productMapper.toEntity(productRequest);
        product.setCategoryProduct(categoryProduct.get());
        product.setUser(optionalUser.get());
        Product save = productRepository.save(product);
        log.info("product with id: {} succesfully created", save.getId());
        return save;
    }
    @Override
    public Product findById(long id){
        log.info("request to get product with id: {}",id);
        Product product = productRepository.findByIdAndIsDeleteFalse(id).orElseThrow(() -> {
            log.debug("product with id: {} not found",id);
           return new ProductNotExistException("product with id: " + id + " does not exist");
        });
        log.info("succesfully found product with id: {}",id);
        return product;
    }

    @Override
    public List<Product> findAll() {
        log.info("request to get product list");
        List<Product> productList = productRepository.findAllByIsDeleteFalse();
        return productList;
    }
    @Override
    public Product update(long id, ProductRequest productRequest) {
        log.info("request to update product with id: {}",id);
        Optional<Product> productById = productRepository.findByIdAndIsDeleteFalse(id);
        Optional<CategoryProduct> categoryProduct = categoryProductRepository.findById(productRequest.getCategoryProductId());
        if (productById.isEmpty()){
            log.debug("product with id: {} not found",id);
            throw new ProductNotExistException("product with id: " + id + " does not exist");
        }
        Product product = productMapper.toEntity(productRequest);
        product.setId(id);
        product.setCategoryProduct(categoryProduct.get());
        product.setUser(productById.get().getUser());
        Product save = productRepository.save(product);
        log.info(" product with id: {} successfully updated",id);
        return save;
    }

    @Override
    public void deleteById(long id) {
        Optional<Product> productById = productRepository.findByIdAndIsDeleteFalse(id);
        log.info("request from user {} to delete product with id: {}",productById.get().getUser().getName(),id);

        if (productById.isPresent()){
            Product product = productById.get();
            product.setDelete(true);
            productRepository.save(product);
            log.info("product with id: {} successfully  deleted",id);
        }
        else {
            log.debug("product with id: {} can't be found",id);
            throw new ProductNotExistException("product with " + id +" don't exist");
        }
    }

    @Override
    public byte[] getImage(@RequestParam("fileName") String fileName)  {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(folderPath + File.separator + fileName);
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return  bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
