package com.mulberry.blackmulberry.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @GetMapping("/product/details")
    public String productDetailsPage(@RequestParam int id){
        // todo logic find product by id
        return "view/product-details";
    }

    @GetMapping("/products")
    public String productFilterPage(){
        // todo find products by filter
        return "view/products-filter";
    }

}
