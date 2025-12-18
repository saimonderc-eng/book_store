package com.example.book_store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("username")
    String username;

    @NotNull(message = "Email is required!")
    @JsonProperty("email")
    String email;

    @NotNull(message = "Full Name is required!")
    @JsonProperty("fullName")
    String fullName;

    @NotNull(message = "Birthdate is required!")
    @JsonProperty("dateOfBirth")
    LocalDate dateOfBirth;

    @NotEmpty(message = "Password cannot be empty!")
    @JsonProperty("password")
    String password;

}