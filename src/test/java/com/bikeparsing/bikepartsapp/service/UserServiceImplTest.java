package com.bikeparsing.bikepartsapp.service;

import com.bikeparsing.bikepartsapp.controller.LoginController;
import com.bikeparsing.bikepartsapp.entity.Authority;
import com.bikeparsing.bikepartsapp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @Test
    public void shouldCreateNewUserWithAuthority() {
        User user = new User();

        user.setUserName("testUser");
        user.setPassword("123");
        user.setEnabled(true);

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("testUser", "ROLE_USER"));
        authorities.add(new Authority("testUser", "ROLE_MANAGER"));

        user.setAuthorities(authorities);

        userService.save(user);

    }
}