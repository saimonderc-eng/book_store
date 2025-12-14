package com.example.book_store.dto;

import com.example.book_store.entity.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemDto {

    BookResponseDto book;
    boolean available;
    String stockLevel;
}
