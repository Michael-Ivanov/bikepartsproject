package com.bikeparsing.bikepartsapp.controller;

import com.bikeparsing.bikepartsapp.entity.Authority;
import com.bikeparsing.bikepartsapp.entity.Item;
import com.bikeparsing.bikepartsapp.entity.Option;
import com.bikeparsing.bikepartsapp.entity.User;
import com.bikeparsing.bikepartsapp.exception.UserNotAuthenticatedException;
import com.bikeparsing.bikepartsapp.parse.strategy.UrlHandler;
import com.bikeparsing.bikepartsapp.service.ProductService;
import com.bikeparsing.bikepartsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ProductService productService;
    private final UserService userService;
    private final UrlHandler urlHandler;

    private Item item;

    @Autowired
    public UserController(ProductService productService, UserService userService, UrlHandler urlHandler) {
        this.productService = productService;
        this.userService = userService;
        this.urlHandler = urlHandler;
    }

    @GetMapping("/homepage")
    public String userAccount(Model model) {

        int userId = getAuthUser().getId();
        List<Item> items = productService.getAllByUserId(userId);
        model.addAttribute("items", items);

        return "/user-pages/user-home-page";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        User user = getAuthUser();

        List<Authority> authList
                = userService.getAuthoritiesByName(user.getUserName());

        System.out.println("showProfile: " + user);
        model.addAttribute("authList", authList);
        model.addAttribute("user", user);
        model.addAttribute("oldUserName", user.getUserName());

        return "/user-pages/user-profile";
    }

    @PostMapping("/add-item")
    public String addItem(@RequestParam("itemUrl") String itemUrl,
                          Model model) {
        item = urlHandler.parsePage(itemUrl);
        model.addAttribute("item", item);

        return "/user-pages/choose-option";
    }

    @PostMapping("/save-item")
    public String saveItem(@RequestParam("optionName") String optionName,
                           @RequestParam("itemName") String itemName) {

        System.out.println(">>> Controller -> check item: " + item);
        Option option = item.getOptionByName(optionName);

        System.out.println(">>> Controller -> check option: " + option);
        item.setName(itemName);
        int userId = getAuthUser().getId();
        item.setUserId(userId);
        item.setSelectedOption(option);

        productService.save(item);
        return "redirect:/user/homepage";
    }

    @GetMapping("/update-item")
    public String updateItem(@RequestParam("itemId") int id, Model model) {
        item = productService.getById(id);
        model.addAttribute("item", item);
        return "/user-pages/choose-option";
    }

    @GetMapping("/delete-item")
    public String deleteItem(@RequestParam("itemId") int id) {
        productService.deleteById(id);
        return "redirect:/user/homepage";
    }

    @PostMapping("/save-user")
    public String saveUser(User user, @RequestParam("oldUserName") String oldUserName) {

        System.out.println("saveUser got user: " + user);
        System.out.println("saveUser oldUserName: " + oldUserName);

        // get user authorities from db
        List<Authority> authorities = userService.getAuthoritiesByName(oldUserName);

        System.out.println("getAuthoritiesByName returns: " + authorities);

        // check if no authorities returned from db, set default
        if (authorities == null || authorities.isEmpty()) {
            authorities = new ArrayList<>();
            authorities.add(new Authority(user.getUserName(), "ROLE_USER"));
        }
        user.setAuthorities(authorities);

        // save user(and his authorities)
        userService.save(user);
        user.reAuthenticate();

        return "redirect:/user/profile";
    }

    private User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String name = authentication.getName();
            System.out.println("getAuthUser: name = " + name);
            return userService.getByName(name);
        }
        throw new UserNotAuthenticatedException("User not authenticated");
    }

    // todo: consider refactoring using security User instead of our custom User
}
