package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductRatingService;
import com.black.mulberry.data.transfer.request.ProductRatingRequest;
import com.black.mulberry.data.transfer.response.ProductRatingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product/rating")
public class ProductRatingEndpoint {

    private final ProductRatingService productRatingService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<List<ProductRatingResponse>> findPaginatedByUserId(
            @AuthenticationPrincipal CurrentUser currentUser,
            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(productRatingService.findPaginatedByUserId(currentUser.getId(), pageable));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/count")
    public ResponseEntity<Long> countAllByUserId(
            @AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(productRatingService.countAllByUserId(currentUser.getId()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/product")
    public ResponseEntity<List<ProductRatingResponse>> findPaginatedByUserId(
            @RequestParam("product_id") long productId,
            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(productRatingService.findPaginatedByProductId(productId, pageable));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/product/count")
    public ResponseEntity<Long> countAllByProductId(@RequestParam("product_id") long productId) {
        return ResponseEntity.ok(productRatingService.countAllByProductId(productId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<ProductRatingResponse>> findPaginated(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(productRatingService.findPaginated(pageable));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/count")
    public ResponseEntity<Long> countAll() {
        return ResponseEntity.ok(productRatingService.countAll());
    }

    @GetMapping("/avg")
    public ResponseEntity<Integer> average(@RequestParam("product_id") long productId) {
        return ResponseEntity.ok(productRatingService.avgProduct(productId));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    public ResponseEntity<ProductRatingResponse> rate(
            @Valid @RequestBody ProductRatingRequest productRatingRequest,
            @AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(productRatingService.rate(productRatingRequest, currentUser.getId()));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/")
    public ResponseEntity<Long> canselRate(
            @RequestParam("product_id") long productId,
            @AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(productRatingService.cancelRating(currentUser.getId(), productId));
    }
}
