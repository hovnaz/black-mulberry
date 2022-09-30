package com.mulberry.blackmulberry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String mainPage(){
        return "view/home";
    }
}
