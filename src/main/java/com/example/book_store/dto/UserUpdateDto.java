package com.example.book_store.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDto {

    @NotEmpty(message = "Username cannot be empty!")
    String username;

    @NotEmpty(message = "Email cannot be empty!")
    String email;

    @NotEmpty(message = "FullName cannot be empty!")
    String fullName;

    LocalDate dateOfBirth;
}