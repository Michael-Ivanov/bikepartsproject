package com.bikeparsing.bikepartsapp.dao;

import com.bikeparsing.bikepartsapp.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AuthorityRepositoryDAO extends JpaRepository<Authority, String> {

    List<Authority> getAllByUserName(String userName);
}
