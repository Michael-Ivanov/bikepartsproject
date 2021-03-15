package com.bikeparsing.bikepartsapp.service;

import com.bikeparsing.bikepartsapp.dao.AuthorityRepositoryDAO;
import com.bikeparsing.bikepartsapp.dao.UserRepositoryDAO;
import com.bikeparsing.bikepartsapp.entity.Authority;
import com.bikeparsing.bikepartsapp.entity.User;
import com.bikeparsing.bikepartsapp.exception.UserNotAuthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryDAO userRepository;

    @Autowired
    private AuthorityRepositoryDAO authRepository;

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public void save(User user) {
        List<Authority> authorities = user.getAuthorities();
        userRepository.save(user);
        for (Authority authority : authorities) {
            authRepository.save(authority);
        }
    }

    @Override
    public void deleteUserById(int id) {

    }

    @Override
    public List<Authority> getAuthoritiesByName(String userName) {
        return authRepository.getAllByUserName(userName);
    }

    @Override
    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new UserNotAuthenticatedException("User not authenticated");
        }
        String name = authentication.getName();
        System.out.println("getAuthUser: name = " + name);
        return getUserByName(name);
    }
}
