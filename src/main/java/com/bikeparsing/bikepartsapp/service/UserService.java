package com.bikeparsing.bikepartsapp.service;


import com.bikeparsing.bikepartsapp.entity.Authority;
import com.bikeparsing.bikepartsapp.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByName(String name);

    void save(User user);

    void deleteUserById(int id);

    List<Authority> getAuthoritiesByName(String userName);

    User getAuthUser();
}
