package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.exception.ProductNotFoundException;
import com.black.mulberry.core.mapper.ProductMapper;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.util.DataGenerator;
import com.black.mulberry.data.transfer.request.ProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;


    @Mock
    private ProductRepository productRepository;

    private Product product;

    private ProductRequest productRequest;

    @BeforeEach
    void init() {
        product = DataGenerator.generateProduct();
        productRequest = DataGenerator.generateProductRequest();
    }

    @Test
    public void findById_successTest() {
        when(productRepository.findByIdAndIsDeleteFalse(anyLong()))
                .thenReturn(Optional.of(new Product()));
        assertDoesNotThrow(() -> productService.findById(555L));
    }

    @Test
    public void findById_throwsProductNotFoundException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService
                .findById(4));
    }

    @Test
    public void update_throwsProductNotFoundException() {
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService
                .update(555L, 1, DataGenerator.generateProductRequest()));
    }
}
