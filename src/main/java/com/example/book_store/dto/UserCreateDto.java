package com.example.book_store.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDto {

    @NotEmpty(message = "Name cannot be empty!")
    String username;

    @NotNull(message = "Email is required!")
    String email;

    @NotNull(message = "Full Name is required!")
    String fullName;

    LocalDate dateOfBirth;

    @NotEmpty(message = "Password cannot be empty!")
    String password;

}