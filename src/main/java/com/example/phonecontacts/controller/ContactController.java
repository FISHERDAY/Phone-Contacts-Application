package com.example.phonecontacts.controller;

import com.example.phonecontacts.model.Contact;
import com.example.phonecontacts.model.User;
import com.example.phonecontacts.model.dto.ContactRequestDto;
import com.example.phonecontacts.model.dto.ContactResponseDto;
import com.example.phonecontacts.model.dto.ContactTransformer;
import com.example.phonecontacts.service.ContactService;
import com.example.phonecontacts.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService contactService;
    private final UserService userService;

    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @GetMapping
    List<ContactResponseDto> get(Principal principal) {
        return userService.readByUsername(principal.getName()).getContacts().stream()
                .map(ContactResponseDto::new).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ContactResponseDto create(@Valid @RequestBody ContactRequestDto contactRequestDto, Principal principal) {
        Contact contact = contactService.isExists(contactRequestDto.getName()) ?
                contactService.readByName(contactRequestDto.getName()) :
                ContactTransformer.convertToEntity(contactRequestDto);
        User user = userService.readByUsername(principal.getName());
        user.getContacts().add(contact);
        userService.update(user);
        return new ContactResponseDto(contactService.readByName(contact.getName()));
    }

    @PutMapping
    ContactResponseDto update(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        Contact contact = ContactTransformer.convertToEntity(contactRequestDto);
        return new ContactResponseDto(contactService.update(contact));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        contactService.delete(contactRequestDto.getName());
    }
}
