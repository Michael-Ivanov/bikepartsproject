package com.bikeparsing.bikepartsapp.service;


import com.bikeparsing.bikepartsapp.entity.Authority;
import com.bikeparsing.bikepartsapp.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(int id);

    User getByName(String name);

    void save(User user);

    void deleteById(int id);

    List<Authority> getAuthoritiesByName(String userName);

    User getAuthUser();
}
