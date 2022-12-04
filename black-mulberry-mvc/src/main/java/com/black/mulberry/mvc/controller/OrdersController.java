package com.black.mulberry.mvc.controller;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.data.transfer.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

    private final ProductService productService;

    @GetMapping("/")
    public String ordersPage(ModelMap modelMap, Pageable pageable){
        // todo find all products in orders
        List<ProductResponse> products = productService.findAll(pageable);
        modelMap.addAttribute("products",products);
        return "view/orders";
    }
}
