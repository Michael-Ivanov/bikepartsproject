package com.bikeparsing.bikepartsapp.service;

import com.bikeparsing.bikepartsapp.dao.UserRepositoryDAO;
import com.bikeparsing.bikepartsapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryDAO dao;

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
        return dao.findByUserName(name);
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void deleteById(int id) {

    }
}
