package com.example.book_store.repository.customRepository;

import com.example.book_store.dto.BookResponseDto;
import com.example.book_store.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomBookRepository {

    List<Book> dynamicSearch(BookResponseDto book);
}
