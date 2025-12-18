package com.example.book_store.mapper;

import com.example.book_store.dto.BookCreateDto;
import com.example.book_store.dto.BookResponseDto;
import com.example.book_store.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponseDto toDto(Book book);

    List<BookResponseDto> toDtoList(List<Book> entities);

    Book toEntity(BookCreateDto dto);

}
