package com.example.phonecontacts.service;

import com.example.phonecontacts.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User create(User user);

    User readById(long id);

    User readByUsername(String name);

    User update(User user);

    void delete(long id);

    UserDetails loadUserByUsername(String username);
}
