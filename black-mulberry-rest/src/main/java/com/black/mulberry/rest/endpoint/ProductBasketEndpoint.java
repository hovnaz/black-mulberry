package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.mapper.ProductBasketMapper;
import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductBasketService;
import com.black.mulberry.data.transfer.request.ProductBasketItemRequest;
import com.black.mulberry.data.transfer.response.ProductBasketItemResponse;
import com.black.mulberry.data.transfer.response.ProductBasketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product/basket")
public class ProductBasketEndpoint {

    private final ProductBasketService productBasketService;
    private final ProductBasketMapper productBasketMapper;

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/user")
//    public ResponseEntity<List<ProductCommentResponse>> findPaginatedByUserId(
//            @RequestParam("user_id") long userId,
//            @PageableDefault Pageable pageable) {
//        return ResponseEntity.ok(productCommentService.findPaginatedByUserId(userId, pageable));
//    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/user/count")
//    public ResponseEntity<Long> countAllPaginatedByUserId(
//            @RequestParam("user_id") long userId) {
//        return ResponseEntity.ok(productCommentService.countAllByUserId(userId));
//    }
//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/user_and_product")
//    public ResponseEntity<List<ProductCommentResponse>> findPaginatedByProductIdAndUserId(
//            @RequestParam("user_id") long userId,
//            @RequestParam("product_id") long productId,
//            @PageableDefault Pageable pageable) {
//        return ResponseEntity.ok(productCommentService.findPaginatedByProductIdAndUserId(productId, userId, pageable));
//    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/user_and_product/count")
//    public ResponseEntity<Long> countAllByProductIdAndUserId(
//            @RequestParam("user_id") long userId,
//            @RequestParam("product_id") long productId) {
//        return ResponseEntity.ok(productCommentService.countAllByProductIdAndUserId(productId, userId));
//    }
//
//    @GetMapping("/product")
//    public ResponseEntity<List<ProductCommentResponse>> findPaginatedByProductId(
//            @RequestParam("product_id") long productId,
//            @PageableDefault Pageable pageable) {
//        return ResponseEntity.ok(productCommentService.findPaginatedByProductId(productId, pageable));
//    }
//
//    @GetMapping("/product/count")
//    public ResponseEntity<Long> countAllByProductId(@RequestParam("product_id") long productId) {
//        return ResponseEntity.ok(productCommentService.countAllByProductId(productId));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBasketResponse> findById(
            @PathVariable long id) {
        ProductBasketResponse productBasketResponse = productBasketMapper.toResponse(productBasketService.findById(id));
        return ResponseEntity.ok(productBasketResponse);
    }

    @PreAuthorize("isAuthenticated()")// +
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductBasketResponse> deleteById(@PathVariable long id, @AuthenticationPrincipal CurrentUser currentUser) {
        ProductBasketResponse productBasketResponse = productBasketService.cancelByProductId(id, currentUser.getId());
        return ResponseEntity.ok(productBasketResponse);
    }

    @PreAuthorize("isAuthenticated()")// +
    @PostMapping("/")
    public ResponseEntity<ProductBasketItemResponse> add(@Valid @RequestBody ProductBasketItemRequest productBasketItemRequest,
                                                         @AuthenticationPrincipal CurrentUser currentUser) {
        ProductBasketItemResponse productBasketItemResponse = productBasketService.add(productBasketItemRequest, currentUser.getId());
        return ResponseEntity.ok(productBasketItemResponse);
    }
}
