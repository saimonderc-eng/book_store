package com.example.book_store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponseDto {

    String name;
    Double price;
    String description;
    String author;
    Integer stockQuantity;
    List<Long> categories;
}
