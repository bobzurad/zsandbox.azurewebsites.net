package com.zurad.java.spring.eazybytes.eazyschool.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String displayHomePage() {
        return "home.html";
    }
}
