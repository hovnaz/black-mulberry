package com.black.mulberry.mvc.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String HomePage(){
        return "view/home";
    }
}
