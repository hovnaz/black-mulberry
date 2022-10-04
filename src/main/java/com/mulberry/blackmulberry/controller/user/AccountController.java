package com.mulberry.blackmulberry.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/account")
    public String accountPage(){
        // todo logic private user page info page
        return "view/product-details";
    }
}
