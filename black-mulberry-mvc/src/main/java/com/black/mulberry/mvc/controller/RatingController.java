package com.black.mulberry.mvc.controller;

import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductRatingService;
import com.black.mulberry.data.transfer.request.ProductRatingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/rating")
public class RatingController {

    private final ProductRatingService productRatingService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/product")
    public String addProductComment(@AuthenticationPrincipal CurrentUser currentUser, @Valid @ModelAttribute ProductRatingRequest productRatingRequest) {
        productRatingService.rate(productRatingRequest, currentUser.getId());
        return "redirect:/v1/product/" + productRatingRequest.getProductId();
    }
}
