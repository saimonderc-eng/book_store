package com.example.book_store.repository;

import com.example.book_store.entity.Book;
import com.example.book_store.repository.customRepository.BookCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookCustomRepository {
    Optional<Book> findBookById(Long id);

    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id IN :categoryIds")
    List<Book> findBooksByCategoriesId(@Param("categoryIds")List<Long> categoryIds);
}
