package com.example.book_store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDto {

    String username;
    String email;
    String fullName;
    LocalDate dateOfBirth;
    String password;

}