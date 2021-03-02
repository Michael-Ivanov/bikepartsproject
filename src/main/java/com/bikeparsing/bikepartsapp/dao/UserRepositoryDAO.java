package com.bikeparsing.bikepartsapp.dao;

import com.bikeparsing.bikepartsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryDAO extends JpaRepository<User, Integer> {

    User findByUserName(String name);
}
