package com.bikeparsing.bikepartsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/account")
    public String userAccount() {
        return "/user_pages/user-account";
    }
}
