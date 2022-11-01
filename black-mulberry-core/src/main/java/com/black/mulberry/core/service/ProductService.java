package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.ProductNotExistException;
import com.black.mulberry.core.mapper.ProductMapper;
import com.black.mulberry.core.repository.CategoryProductRepository;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
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
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryProductRepository categoryProductRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProductMapper productMapper;

    private  String folderPath="D://black-mulberry//images//product";

    @GetMapping(value = "/product/getProductPic", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    public Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }

    public ProductResponse saveProduct(ProductRequest productRequest) {
        Optional<User> optionalUser = userRepository.findById(productRequest.getUserId());
        Optional<CategoryProduct> categoryProduct = categoryProductRepository.findById(productRequest.getCategoryProductId());
        Product product = Product.builder()
                .title(productRequest.getTitle())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .picUrl(productRequest.getPicUrl())
                .description(productRequest.getDescription())
                .categoryProduct(categoryProduct.get())
                .user(optionalUser.get())
                .build();
        Product save = productRepository.save(product);
        return productMapper.toResponse(save);
    }


    public List<ProductResponse> findAllProducts() {
        List<Product> productList = productRepository.findAllByIsDeleteFalse();
        return productList.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void deleteProduct(long id){
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()){
            Product product = byId.get();
            product.setDelete(true);
            productRepository.save(product);
        }
        throw new ProductNotExistException("product with" + id +"don't exist");
    }

    public ProductResponse findById(long id){
        Product product = productRepository.findByIdAndIsDeleteFalse(id).orElseThrow(() -> new ProductNotExistException(
                "product with id: " + id + " does not exist"
        ));
        return productMapper.toResponse(product);
    }

    public ProductResponse update(long id, ProductRequest productRequest) {
        Optional<Product> productById = productRepository.findByIdAndIsDeleteFalse(id);
        Optional<CategoryProduct> categoryProduct = categoryProductRepository.findById(productRequest.getCategoryProductId());
        if (productById.isEmpty()){
            throw new ProductNotExistException("product with id " + id + " does not exist");
        }
        Product product = productById.get();
        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDescription(productRequest.getDescription());
        product.setPicUrl(productRequest.getPicUrl());
        product.setCategoryProduct(categoryProduct.get());
        Product save = productRepository.save(product);

        return productMapper.toResponse(save);
    }


}
