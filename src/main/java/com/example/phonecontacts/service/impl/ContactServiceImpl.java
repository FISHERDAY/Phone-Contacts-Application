package com.example.phonecontacts.service.impl;

import com.example.phonecontacts.model.Contact;
import com.example.phonecontacts.repository.ContactRepository;
import com.example.phonecontacts.service.ContactService;
import com.example.phonecontacts.exception.NullEntityReferenceException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact create(Contact contact) {
        if (Objects.isNull(contact)) throw new NullEntityReferenceException("Contact cannot be null");
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        Contact updatedContact = readByName(contact.getName());
        updatedContact.setEmails(contact.getEmails());
        updatedContact.setPhoneNumbers(contact.getPhoneNumbers());
        return contactRepository.save(updatedContact);
    }

    @Override
    public Contact readById(long id) {
        return contactRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Contact with id" + id + " not found"));
    }

    @Override
    public Contact readByName(String name) {
        return contactRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Contact with name " + name + " not found"));
    }

    @Override
    public void delete(String name) {
        contactRepository.delete(readByName(name));
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public boolean isExists(String name) {
        return contactRepository.existsByName(name);
    }
}
