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
        User newUser = new User();

        model.addAttribute("user", new User());

        // todo: proceed with refactoring /register-form. Add new 'ROLE-USER' authority after constructing new user

        return "/register-form";
    }
}
