package com.mulberry.blackmulberry.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("account")
@Controller
public class AccountController {

    @GetMapping("/")
    public String accountPage(){
        return "view/my-account";
    }
}
