package com.example.phonecontacts.controller;

import com.example.phonecontacts.model.User;
import com.example.phonecontacts.model.dto.AuthRequest;
import com.example.phonecontacts.model.dto.AuthResponse;
import com.example.phonecontacts.security.JwtTokenUtil;
import com.example.phonecontacts.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@Valid @RequestBody AuthRequest authRequest) {
        log.info("Create authorization token");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            log.warn("Credentials is not valid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        log.info("Authorization token successfully created");
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRequest authRequest) {
        log.info("Register new user");
        try {
            userService.loadUserByUsername(authRequest.getUsername());
            log.warn("User with this username is already exists");
            return ResponseEntity.badRequest().body("Username is already taken");
        } catch (EntityNotFoundException e) {
            User newUser = new User();
            newUser.setUsername(authRequest.getUsername());
            newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            userService.create(newUser);
            UserDetails userDetails = userService.loadUserByUsername(newUser.getUsername());
            String token = jwtTokenUtil.generateToken(userDetails);
            log.info("New user successfully registered");
            return ResponseEntity.ok(new AuthResponse(token));
        }
    }
}
