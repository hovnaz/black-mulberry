package com.mulberry.blackmulberry.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {
    @GetMapping("/orders")
    public String ordersPage(){
        // todo find all products in orders
        return "view/orders";
    }
}
