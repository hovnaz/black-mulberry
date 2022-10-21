package com.mulberry.blackmulberry.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @GetMapping("/")
    public String basketPage(){
        // todo find all products in basket
        return "view/basket";
    }
}
