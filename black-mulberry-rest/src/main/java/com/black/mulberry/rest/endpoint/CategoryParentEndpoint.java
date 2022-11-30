package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.entity.CategoryParent;
import com.black.mulberry.core.mapper.CategoryParentMapper;
import com.black.mulberry.core.service.CategoryParentService;
import com.black.mulberry.data.transfer.request.CategoryParentRequest;
import com.black.mulberry.data.transfer.response.CategoryParentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categoryParent")
public class CategoryParentEndpoint {

    private final CategoryParentService categoryParentService;
    private final CategoryParentMapper categoryParentMapper;

    @GetMapping
    public List<CategoryParentResponse> getAllCategoryParent() {
        return categoryParentService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> saveCategoryParent(@RequestBody CategoryParentRequest categoryParentRequest) {
        return ResponseEntity.ok(categoryParentMapper.toResponse(categoryParentService.save(categoryParentRequest)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryParentById(@PathVariable("id") int id) {
        categoryParentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryParentResponse> updateProduct(@PathVariable("id") long id, @RequestBody CategoryParentRequest categoryParentRequest) {
        CategoryParent updatedCategoryParent = categoryParentService.update(id, categoryParentRequest);
        return ResponseEntity.ok(categoryParentMapper.toResponse(updatedCategoryParent));
    }
}
