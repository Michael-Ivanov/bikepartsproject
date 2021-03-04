package com.bikeparsing.bikepartsapp.controller;

import com.bikeparsing.bikepartsapp.entity.Item;
import com.bikeparsing.bikepartsapp.entity.User;
import com.bikeparsing.bikepartsapp.service.ProductService;
import com.bikeparsing.bikepartsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public UserController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/homepage")
    public String userAccount(Model model) {

        List<Item> items = productService.getAllByUserId(getAuthUserId());
        model.addAttribute("items", items);

        return "/user-pages/user-home-page";
    }

    @PostMapping("/add-item")
    public String addItem(Model model) {
        model.addAttribute("item",  new Item());
        return "/user-pages/add-new-item";
    }

    @PostMapping("/save-item")
    public String saveItem(Item item) {

        item.setUserId(getAuthUserId());
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

    @GetMapping("/delete-item")
    public String deleteItem(@RequestParam("itemId") int id) {
        productService.deleteById(id);
        return "redirect:/user/homepage";
    }


    private int getAuthUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String name = authentication.getName();
            User user = userService.getByName(name);
            return user.getId();
        }
        throw new RuntimeException("User not authenticated.");
    }
}
