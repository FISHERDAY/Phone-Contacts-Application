package com.example.phonecontacts.service;

import com.example.phonecontacts.model.Contact;

import java.util.List;

public interface ContactService {
    Contact create(Contact contact);
    Contact readById(long id);
    Contact readByName(String name);
    Contact update(Contact contact);
    void delete(String name);
    List<Contact> getAll();
    boolean isExists(String name);
}
