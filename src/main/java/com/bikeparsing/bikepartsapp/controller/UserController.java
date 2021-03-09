package com.bikeparsing.bikepartsapp.controller;

import com.bikeparsing.bikepartsapp.entity.Item;
import com.bikeparsing.bikepartsapp.entity.Option;
import com.bikeparsing.bikepartsapp.entity.User;
import com.bikeparsing.bikepartsapp.parse.strategy.UrlHandler;
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
    private final UrlHandler urlHandler;

    @Autowired
    public UserController(ProductService productService, UserService userService, UrlHandler urlHandler) {
        this.productService = productService;
        this.userService = userService;
        this.urlHandler = urlHandler;
    }

    @GetMapping("/homepage")
    public String userAccount(Model model) {

        List<Item> items = productService.getAllByUserId(getAuthUserId());
        model.addAttribute("items", items);

        return "/user-pages/user-home-page";
    }

    @PostMapping("/add-item")
    public String addItem(@RequestParam("itemUrl") String itemUrl,
                          Model model) {
        Item item = urlHandler.parsePage(itemUrl);

        model.addAttribute("myItem", item); // todo: rename back to 'item'
        model.addAttribute("myOptions", item.getOptions());
        model.addAttribute("selectedOption", item.getSelectedOption());

        return "/user-pages/choose-option";

    }

    @PostMapping("/save-item")
    public String saveItem(@ModelAttribute("myItem") Item item) {

        item.setUserId(getAuthUserId());
        System.out.println(">>> Controller -> check item before saving: " + item);
        productService.save(item);


        return "redirect:/user/homepage";
    }

    @GetMapping("/update-item")
    public String updateItem(@RequestParam("itemId") int id,
                             Model model) {
        Item item = productService.getById(id);
        model.addAttribute("item", item);
        return "/user-pages/item-form";
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
