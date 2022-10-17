package com.mulberry.blackmulberry.controller.user;

import com.mulberry.blackmulberry.entity.Product;
import com.mulberry.blackmulberry.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrdersController {
    private final ProductRepository productRepository;

    @GetMapping("/orders")
    public String ordersPage(ModelMap modelMap){
        // todo find all products in orders
        List<Product> products = productRepository.findAll();
        modelMap.addAttribute("products",products);
        return "view/orders";
    }

}
