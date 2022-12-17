package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.mapper.CategoryParentMapper;
import com.black.mulberry.core.mapper.CategoryProductMapper;
import com.black.mulberry.core.repository.CategoryParentRepository;
import com.black.mulberry.core.repository.CategoryProductRepository;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.service.CategoryParentService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CategoryProductServiceImplTest {

    @InjectMocks
    private CategoryProductServiceImpl categoryProductService;
    @Mock
    private CategoryProductRepository categoryProductRepository;
    @Mock
    private CategoryParentService categoryParentService;
    @Mock
    private CategoryProductMapper categoryProductMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryParentRepository categoryParentRepository;
    @Mock
    private CategoryParentMapper categoryParentMapper;
    @Mock
    private ModelMapper modelMapper;

//    private CategoryProduct categoryProduct;
//
//    private CategoryProductRequest categoryProductRequest;
//
//    @BeforeEach
//    void init() {
//        categoryProduct = DataGenerator.generateCategoryProduct();
//        categoryProductRequest = DataGenerator.generateCategoryProductRequest();
//    }
//
//    @Test
//    void update_successTest() {
//        when(categoryProductRepository.findById(anyLong()))
//                .thenReturn(Optional.empty());
//        when(categoryProductRepository.save(any(CategoryProduct.class)))
//                .thenReturn(new CategoryProduct());
//        assertDoesNotThrow(() -> categoryProductService.update(1, categoryProductRequest));
//        assertEquals(categoryProduct.getName(), categoryProductRequest.getName());
//    }
//
//    @Test
//    void update_throwsUserNotFoundException() {
//        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
//        assertThrows(UserNotFoundException.class, () -> userService
//                .update(DataGenerator.generateUserUpdateRequest(), 555L));
//    }

}
