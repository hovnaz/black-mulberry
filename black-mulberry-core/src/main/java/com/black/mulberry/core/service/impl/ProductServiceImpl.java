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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryProductRepository categoryProductRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @Value("blackMulberry.product.images")
    private String folderPath;

    @Override
    public ProductResponse save(ProductRequest productRequest) {
        Optional<User> optionalUser = userRepository.findById(productRequest.getUserId());
        Optional<CategoryProduct> categoryProduct = categoryProductRepository.findById(productRequest.getCategoryProductId());
        if(optionalUser.isEmpty() || categoryProduct.isEmpty()){
           throw new NullPointerException("user or category is null");
        }
        Product product = productMapper.toEntity(productRequest);
        product.setCategoryProduct(categoryProduct.get());
        product.setUser(optionalUser.get());
        Product save = productRepository.save(product);
        return productMapper.toResponse(save);
    }
    @Override
    public ProductResponse findById(long id){
        Product product = productRepository.findByIdAndIsDeleteFalse(id).orElseThrow(() -> new ProductNotExistException(
                "product with id: " + id + " does not exist"
        ));
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> findAll() {
        List<Product> productList = productRepository.findAllByIsDeleteFalse();
        return productList.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }
    @Override
    public ProductResponse update(long id, ProductRequest productRequest) {
        Optional<Product> productById = productRepository.findByIdAndIsDeleteFalse(id);
        Optional<CategoryProduct> categoryProduct = categoryProductRepository.findById(productRequest.getCategoryProductId());
        if (productById.isEmpty()){
            throw new ProductNotExistException("product with id: " + id + " does not exist");
        }
        Product product = productMapper.toEntity(productRequest);
        product.setId(id);
        product.setCategoryProduct(categoryProduct.get());
        product.setUser(productById.get().getUser());
        Product save = productRepository.save(product);
        return productMapper.toResponse(save);
    }

    @Override
    public void deleteById(long id) {
        Optional<Product> productById = productRepository.findByIdAndIsDeleteFalse(id);
        if (productById.isPresent()){
            Product product = productById.get();
            product.setDelete(true);
            productRepository.save(product);
        }
        else {
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
