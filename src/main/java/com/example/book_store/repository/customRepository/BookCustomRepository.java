package com.example.book_store.repository.customRepository;

import com.example.book_store.dto.BookResponseDto;
import com.example.book_store.entity.Book;

import java.util.List;

public interface BookCustomRepository {

    List<Book> dynamicSearch(BookResponseDto book);
}
