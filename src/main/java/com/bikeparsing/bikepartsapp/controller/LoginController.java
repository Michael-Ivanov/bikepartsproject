package com.bikeparsing.bikepartsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/access-denied";
    }
}
