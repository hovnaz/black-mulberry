package com.mulberry.blackmulberry.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasketController {

    @GetMapping("/basket")
    public String basketPage(){
        // todo find all products in basket
        return "basket";
    }
}
