package com.example.book_store.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateBookDto {

    @NotEmpty(message = "Name is required!")
    String name;

    @NotNull(message = "Price is required!")
    @Positive(message = "Price must be greater than 0!")
    Double price;

    @NotBlank(message = "Description is required!")
    String description;

    @NotNull(message = "Author is required!")
    String author;

    @NotNull(message = "Stock quantity is required!")
    @PositiveOrZero(message = "Stock quantity cannot be negative!")
    Integer stockQuantity;

    @NotEmpty(message = "at least 1 category is required!")
    List<CategoryDto> categories;
}
