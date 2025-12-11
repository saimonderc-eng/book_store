package com.example.book_store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordDto {

    Long userId;
    String currentPassword;
    String newPassword;
    String newPasswordConfirmation;
}
