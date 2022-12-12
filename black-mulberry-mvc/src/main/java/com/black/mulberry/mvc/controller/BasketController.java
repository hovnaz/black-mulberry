package com.black.mulberry.mvc.controller;

import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductBasketService;
import com.black.mulberry.data.transfer.request.ProductBasketItemRequest;
import com.black.mulberry.mvc.util.MapUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/v1/basket")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class BasketController {

    private final ProductBasketService productBasketService;
    private final MapUtil mapUtil;

    @GetMapping
    public String basketPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap,
                             @PageableDefault Pageable pageable) {
        Map<String, Object> map = mapUtil.basketDetail(currentUser.getId(), pageable);
        modelMap.addAttribute("data", map);
        return "view/basket";
    }

    @PostMapping("/add")
    public String addProductToBasket(@Valid @ModelAttribute ProductBasketItemRequest productBasketItemRequest,
                                     @AuthenticationPrincipal CurrentUser currentUser) {
        productBasketService.add(productBasketItemRequest, currentUser.getId());
        return "redirect:/v1/product/" + productBasketItemRequest.getProductId();
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute ProductBasketItemRequest productBasketItemRequest,
                         @AuthenticationPrincipal CurrentUser currentUser) {
        productBasketService.update(productBasketItemRequest, currentUser.getId());
        return "redirect:/v1/basket";
    }

    @GetMapping("/cansel/{id}")
    public String canselByProductId(@PathVariable long id, @AuthenticationPrincipal CurrentUser currentUser) {
        productBasketService.cancelByProductId(currentUser.getId(), id);
        return "redirect:/v1/basket";
    }

    @GetMapping("/cansel")
    public String clear(@AuthenticationPrincipal CurrentUser currentUser) {
        productBasketService.clear(currentUser.getId());
        return "redirect:/v1/basket";
    }
}
