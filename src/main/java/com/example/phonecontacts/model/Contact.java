package com.example.phonecontacts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private List<Email> emails = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @ManyToMany(mappedBy = "contacts")
    private List<User> users = new ArrayList<>();
}
