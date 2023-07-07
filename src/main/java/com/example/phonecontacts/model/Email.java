package com.example.phonecontacts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = "[\\w\\.-]+@[\\w\\.-]+\\.[A-Za-z]+")
    private String email;

    public Email(String email) {
        this.email = email;
    }
}
