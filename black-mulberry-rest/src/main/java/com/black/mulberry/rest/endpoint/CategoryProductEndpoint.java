package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.core.mapper.CategoryProductMapper;
import com.black.mulberry.core.service.CategoryProductService;
import com.black.mulberry.data.transfer.request.CategoryProductRequest;
import com.black.mulberry.data.transfer.response.CategoryProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categoryProduct")
public class CategoryProductEndpoint {

    private final CategoryProductService categoryProductService;
    private final CategoryProductMapper categoryProductMapper;

    @GetMapping
    public List<CategoryProductResponse> getAllCategoryProduct() {
        return categoryProductService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> saveCategoryProduct(@Valid @RequestBody CategoryProductRequest categoryProductRequest) {
        return ResponseEntity.ok(categoryProductMapper.toResponse(categoryProductService.save(categoryProductRequest)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryProductById(@PathVariable("id") int id) {
        categoryProductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryProductResponse> updateProduct(@PathVariable("id") long id, @Valid @RequestBody CategoryProductRequest categoryProductRequest) {
        CategoryProduct updatedCategoryProduct = categoryProductService.update(id, categoryProductRequest);
        return ResponseEntity.ok(categoryProductMapper.toResponse(updatedCategoryProduct));
    }
}
