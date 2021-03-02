package com.bikeparsing.bikepartsapp.controller;

import com.bikeparsing.bikepartsapp.entity.Item;
import com.bikeparsing.bikepartsapp.entity.User;
import com.bikeparsing.bikepartsapp.service.ProductService;
import com.bikeparsing.bikepartsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private ProductService productService;
    private UserService userService;

    @Autowired
    public UserController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/account")
    public String userAccount(Authentication authentication,
                              Model model) {

        String name = authentication.getName();
        System.out.println(name);
        User user = userService.getByName(name);
        System.out.println("Retrieved user: " + user);
        int id = user.getId();

        List<Item> items = productService.getAllById(id);
        model.addAttribute("items", items);

        return "/user_pages/user-account";
    }

    @PostMapping("/add_item")
    public String addItem(Model model) {

        Item item = new Item();
        model.addAttribute("item", item);

        return "/user_pages/add-new-item";
    }

}
