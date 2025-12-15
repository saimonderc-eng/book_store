package com.example.book_store.controller;

import com.example.book_store.dto.BookCreateDto;
import com.example.book_store.dto.BookResponseDto;
import com.example.book_store.dto.UpdateBookDto;
import com.example.book_store.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponseDto> getBooks() {
        return bookService.getBooks();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @PostMapping("/addBook")
    public BookResponseDto createBook(@Valid @RequestBody BookCreateDto book) {
        return bookService.createBook(book);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody UpdateBookDto dto) {
        BookResponseDto dto1 = bookService.updateBook(id, dto);
        return ResponseEntity.ok(dto1);
    }

    @GetMapping("/find-by-categories")
    public List<BookResponseDto> findByCategories(@RequestParam List<Long> categoryIds) {
        return bookService.findBooksByCategories(categoryIds);
    }

    @PostMapping("/dynamic-search")
    public ResponseEntity<List<BookResponseDto>> dynamicSearch(@RequestBody BookResponseDto book) {
        List<BookResponseDto> books = bookService.dynamicSearch(book);
        return ResponseEntity.ok(books);
    }
}