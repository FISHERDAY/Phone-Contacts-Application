package com.example.phonecontacts.repository;

import com.example.phonecontacts.model.Contact;
import com.example.phonecontacts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
