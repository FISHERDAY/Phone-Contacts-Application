package com.example.phonecontacts.model.dto;

import com.example.phonecontacts.model.Contact;
import com.example.phonecontacts.model.Email;
import com.example.phonecontacts.model.PhoneNumber;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ContactResponseDto {
    Long id;
    String name;
    List<String> emails;
    List<String> phoneNumbers;

    public ContactResponseDto(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.emails = contact.getEmails().stream()
                .map(Email::getEmail)
                .collect(Collectors.toList());
        this.phoneNumbers = contact.getPhoneNumbers().stream()
                .map(PhoneNumber::getPhoneNumber)
                .collect(Collectors.toList());
    }
}
