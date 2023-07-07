package com.example.phonecontacts.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ContactRequestDto {
    @Pattern(regexp = "([A-z][a-z]+\\s?)+")
    @NotEmpty(message = "The 'name' cannot be empty")
    String name;

    List<String> emails;
    List<String> phoneNumbers;
}
