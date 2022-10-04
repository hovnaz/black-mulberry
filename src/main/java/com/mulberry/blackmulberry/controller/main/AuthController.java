package com.mulberry.blackmulberry.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/auth")
    public String authPage(){
        return "auth";
    }
    // todo logic for login/registration

}
