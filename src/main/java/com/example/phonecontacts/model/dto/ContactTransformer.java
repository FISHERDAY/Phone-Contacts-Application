package com.example.phonecontacts.model.dto;

import com.example.phonecontacts.model.Contact;
import com.example.phonecontacts.model.Email;
import com.example.phonecontacts.model.PhoneNumber;

import java.util.stream.Collectors;

public class ContactTransformer {
    public static Contact convertToEntity(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        contact.setName(contactRequestDto.getName());
        contact.setEmails(contactRequestDto.getEmails().stream()
                .map(Email::new)
                .collect(Collectors.toList()));
        contact.setPhoneNumbers(contactRequestDto.getPhoneNumbers().stream()
                .map(PhoneNumber::new)
                .collect(Collectors.toList()));
        return contact;
    }
}
