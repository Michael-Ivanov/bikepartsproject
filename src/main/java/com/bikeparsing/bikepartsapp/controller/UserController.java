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

    @GetMapping("/homepage")
    public String userAccount(Authentication auth,
                              Model model) {

        String userName = auth.getName();
        int id = getUserId(userName);

        List<Item> items = productService.getAllByUserId(id);
        model.addAttribute("items", items);

        return "/user-pages/user-home-page";
    }

    @PostMapping("/add-item")
    public String addItem(Model model) {
        model.addAttribute("item",  new Item());
        return "/user-pages/add-new-item";
    }

//    @PostMapping("/save-item")
//    public String saveItem(@RequestParam Map<String, String> params,
//                           Authentication auth) {
//
//        Item item = makeItemFromParams(params);
//
//        // todo: in case of updating item we need @RequestBody, fix
//
//        String name = auth.getName();
//        int id = getUserId(name);
//
//        item.setUserId(id);
//        System.out.println(item);
//
//        productService.save(item);
//
//        return "redirect:/user/homepage";
//    }

    @PostMapping("/save-item")
    public String saveItem(Item item,
                           Authentication auth) {

        String name = auth.getName();
        int id = getUserId(name);

        item.setUserId(id);
        System.out.println(item);

        productService.save(item);

        return "redirect:/user/homepage";
    }

    @GetMapping("/update-item")
    public String updateItem(@RequestParam("itemId") int id,
                             Model model) {
        Item item = productService.getById(id);
        model.addAttribute("item", item);
        return "/user-pages/add-new-item"; // todo: refactor name (to 'item-form', or something like that)
    }

    private Item makeItemFromParams(Map<String, String> params) {
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
