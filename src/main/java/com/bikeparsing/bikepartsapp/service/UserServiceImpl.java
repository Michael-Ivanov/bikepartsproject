package com.bikeparsing.bikepartsapp.service;

import com.bikeparsing.bikepartsapp.dao.AuthorityRepositoryDAO;
import com.bikeparsing.bikepartsapp.dao.UserRepositoryDAO;
import com.bikeparsing.bikepartsapp.entity.Authority;
import com.bikeparsing.bikepartsapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Autowired
    private AuthorityRepositoryDAO authRepositoryDAO;

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User getByName(String name) {
        return userRepositoryDAO.findByUserName(name);
    }

    @Override
    public void save(User user) {
        List<Authority> authorities = user.getAuthorities();
        userRepositoryDAO.save(user);
        for (Authority authority : authorities) {
            authRepositoryDAO.save(authority);
        }
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Authority> getAuthoritiesByName(String userName) {
        return authRepositoryDAO.getAllByUserName(userName);
    }
}
