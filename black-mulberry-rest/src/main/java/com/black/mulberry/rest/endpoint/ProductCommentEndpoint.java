package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.mapper.ProductCommentMapper;
import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductCommentService;
import com.black.mulberry.data.transfer.request.ProductCommentRequest;
import com.black.mulberry.data.transfer.response.ProductCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product/comment")
public class ProductCommentEndpoint {

    private final ProductCommentService productCommentService;
    private final ProductCommentMapper productCommentMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<List<ProductCommentResponse>> findPaginatedByUserId(
            @RequestParam("user_id") long userId,
            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(productCommentService.findPaginatedByUserId(userId, pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/count")
    public ResponseEntity<Long> countAllPaginatedByUserId(
            @RequestParam("user_id") long userId) {
        return ResponseEntity.ok(productCommentService.countAllByUserId(userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user_and_product")
    public ResponseEntity<List<ProductCommentResponse>> findPaginatedByProductIdAndUserId(
            @RequestParam("user_id") long userId,
            @RequestParam("product_id") long productId,
            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(productCommentService.findPaginatedByProductIdAndUserId(productId, userId, pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user_and_product/count")
    public ResponseEntity<Long> countAllByProductIdAndUserId(
            @RequestParam("user_id") long userId,
            @RequestParam("product_id") long productId) {
        return ResponseEntity.ok(productCommentService.countAllByProductIdAndUserId(productId, userId));
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductCommentResponse>> findPaginatedByProductId(
            @RequestParam("product_id") long productId,
            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(productCommentService.findPaginatedByProductId(productId, pageable));
    }

    @GetMapping("/product/count")
    public ResponseEntity<Long> countAllByProductId(@RequestParam("product_id") long productId) {
        return ResponseEntity.ok(productCommentService.countAllByProductId(productId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCommentResponse> findById(
            @PathVariable long id) {
        ProductCommentResponse productCommentResponse = productCommentMapper.toResponse(productCommentService.findById(id));
        return ResponseEntity.ok(productCommentResponse);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductCommentResponse> deleteById(@PathVariable long id, @AuthenticationPrincipal CurrentUser currentUser) {
        ProductCommentResponse productCommentResponse = productCommentService.deleteById(id, currentUser.getId());
        return ResponseEntity.ok(productCommentResponse);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    public ResponseEntity<ProductCommentResponse> add(@Valid @RequestBody ProductCommentRequest productCommentRequest,
                                                      @AuthenticationPrincipal CurrentUser currentUser) {
        ProductCommentResponse productCommentResponse = productCommentService.add(productCommentRequest, currentUser.getId());
        return ResponseEntity.ok(productCommentResponse);
    }
}
