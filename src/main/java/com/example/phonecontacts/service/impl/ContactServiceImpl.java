package com.example.phonecontacts.service.impl;

import com.example.phonecontacts.model.Contact;
import com.example.phonecontacts.repository.ContactRepository;
import com.example.phonecontacts.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        Contact updatedContact = findByName(contact.getName());
        updatedContact.setEmails(contact.getEmails());
        updatedContact.setPhoneNumbers(contact.getPhoneNumbers());
        return contactRepository.save(updatedContact);
    }

    @Override
    public Contact readById(long id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public void delete(long id) {
        contactRepository.delete(readById(id));
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findByName(String name) {
        return contactRepository.findByName(name).get();
    }
}
