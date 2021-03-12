package com.bikeparsing.bikepartsapp.controller;

import com.bikeparsing.bikepartsapp.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/register-form";
    }
}
