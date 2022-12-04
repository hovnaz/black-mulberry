package com.black.mulberry.mvc.controller;

import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.data.transfer.response.ProductResponse;
import com.black.mulberry.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
public class AdminController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/users")
    public String getAllUsers(ModelMap modelMap){
        List<UserResponse> users = userService.findAll();
        modelMap.addAttribute("users", users);
        return "view/allUsersList";
    }

    @GetMapping("/products")
    public String getAllProducts(ModelMap modelMap, @PageableDefault Pageable pageable){
        List<ProductResponse> products = productService.findAll(pageable);
        modelMap.addAttribute("products", products);
        return "view/allProductsList";
    }
}
