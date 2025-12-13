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

    //получение списка всех книг
    @GetMapping
    public List<BookResponseDto> getBooks(){
        return bookService.getBooks();
    }

    //добавление книги
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/addBook")
    public BookResponseDto createBook(@Valid @RequestBody BookCreateDto book) {
         return bookService.createBook(book);
    }
    //удаление книги
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    //изменение данных книги
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody UpdateBookDto dto){
        BookResponseDto dto1 = bookService.updateBook(id, dto);
        return ResponseEntity.ok(dto1);
    }
    // поиск книги по категориям
    @GetMapping("/find-by-categories")
    public List<BookResponseDto> findByCategories(@RequestParam List<Long> categoryIds){
       return bookService.findBooksByCategories(categoryIds);
    }
    //поиск книг по критериям
    @GetMapping("/dynamic-search")
    public BookResponseDto dynamicSearch(@RequestBody BookResponseDto dto) throws Exception{

    }
}