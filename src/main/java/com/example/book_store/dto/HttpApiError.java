package com.example.book_store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class HttpApiError {

    String message;
    int code;
    @Builder.Default
    LocalDateTime timestamp = LocalDateTime.now();
}