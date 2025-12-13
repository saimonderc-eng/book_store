package com.example.book_store.mapper;

import com.example.book_store.dto.BookCreateDto;
import com.example.book_store.dto.BookResponseDto;
import com.example.book_store.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "stockLevel", expression = "book.getStockLevel")
    @Mapping(source = "category.id", target = "categoryId")
    BookResponseDto toDto(Book book);

    List<BookResponseDto> toDtoList(List<Book> entities);

    @Mapping(source = "categoryId", target = "category.id")
    Book toEntity(BookCreateDto dto);
}
