package com.example.phonecontacts.service;

import com.example.phonecontacts.model.Contact;

import java.util.List;

public interface ContactService {
    Contact create(Contact contact);
    Contact readById(long id);
    Contact update(Contact contact);
    void delete(long id);
    List<Contact> getAll();
    Contact findByName(String name);
}
