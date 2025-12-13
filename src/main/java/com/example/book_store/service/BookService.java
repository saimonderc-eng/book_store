package com.example.book_store.service;

import com.example.book_store.dto.BookCreateDto;
import com.example.book_store.dto.BookResponseDto;
import com.example.book_store.dto.UpdateBookDto;
import com.example.book_store.entity.Book;
import com.example.book_store.exception.NotFoundException;
import com.example.book_store.mapper.BookMapper;
import com.example.book_store.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookResponseDto> getBooks() {
        List<Book> books  = bookRepository.findAll();

        return bookMapper.toDtoList(books);
    }

    public BookResponseDto createBook(@Valid BookCreateDto book){
        Book book1 =  bookMapper.toEntity(book);
        Book newBook = bookRepository.save(book1);
        return bookMapper.toDto(newBook);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("book not found"));
        bookRepository.delete(book);
    }

    public BookResponseDto updateBook(Long id, UpdateBookDto dto) {
        Book book1 = bookRepository.findBookById(id)
                .orElseThrow(() -> new NotFoundException("book not found"));
        safetySaveValue(dto.getName(), book1::setName);
        safetySaveValue(dto.getAuthor(),book1::setAuthor);
        safetySaveValue(dto.getPrice(), book1::setPrice);
        safetySaveValue(dto.getDescription(), book1::setDescription);

        bookRepository.save(book1);

        return bookMapper.toDto(book1);
    }

    public List<BookResponseDto> findBooksByCategories(List<Long> categoryIds) {
        return bookRepository.findBooksByCategoriesId(categoryIds).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
    public <T> void safetySaveValue(T value, Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }
}
