package com.bikeparsing.bikepartsapp.controller;

import com.bikeparsing.bikepartsapp.entity.Item;
import com.bikeparsing.bikepartsapp.entity.User;
import com.bikeparsing.bikepartsapp.service.ProductService;
import com.bikeparsing.bikepartsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public String userAccount(Authentication auth,
                              Model model) {

        String userName = auth.getName();
        int id = getUserId(userName);

        List<Item> items = productService.getAllById(id);
        model.addAttribute("items", items);

        return "/user_pages/user-account";
    }

    @PostMapping("/add_item")
    public String addItem(Model model) {
        model.addAttribute("item",  new Item());
        return "/user_pages/add-new-item";
    }

    @PostMapping("save_item")
    public String saveItem(@RequestParam Map<String, String> params,
                           Authentication auth) {
        Item item = getItemFromParams(params);

        String name = auth.getName();
        int id = getUserId(name);

        item.setUserId(id);
        System.out.println(item);

        productService.save(item);

        return "redirect:/user/account";
    }

    private Item getItemFromParams(Map<String, String> params) {
        return new Item(
                params.get("name"),
                params.get("price"),
                params.get("itemUrl")
        );
    }

    private int getUserId(String name) {
        User user = userService.getByName(name);
        return user.getId();
    }
}
