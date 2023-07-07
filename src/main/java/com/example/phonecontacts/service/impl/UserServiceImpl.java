package com.example.phonecontacts.service.impl;

import com.example.phonecontacts.exception.NullEntityReferenceException;
import com.example.phonecontacts.model.User;
import com.example.phonecontacts.repository.UserRepository;
import com.example.phonecontacts.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (Objects.isNull(user)) throw new NullEntityReferenceException("User cannot be null");
        return userRepository.save(user);
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User update(User user) {
        if (Objects.isNull(user)) throw new NullEntityReferenceException("User cannot be null");
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public User readByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("User with username " + username + " not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("User with username " + username + " not found"));
    }
}
