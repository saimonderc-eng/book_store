package com.example.book_store.repository;

import com.example.book_store.entity.Book;
import com.example.book_store.entity.CartItem;
import com.example.book_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByUserAndBook(User user, Book book);

    List<CartItem> findAllByUserId(Long userId);
}
