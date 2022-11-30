package com.black.mulberry.mvc.controller;

import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductCommentService;
import com.black.mulberry.data.transfer.request.ProductCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
public class CommentController {

    private final ProductCommentService productCommentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/product")
    public String addProductComment(@Valid @ModelAttribute ProductCommentRequest productCommentRequest,
                                    @AuthenticationPrincipal CurrentUser currentUser) {
        productCommentService.add(productCommentRequest, currentUser.getId());
        return "redirect:/v1/product/" + productCommentRequest.getProductId();
    }
}
