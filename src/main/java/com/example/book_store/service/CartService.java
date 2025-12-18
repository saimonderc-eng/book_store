package com.example.book_store.service;

import com.example.book_store.dto.CartItemDto;
import com.example.book_store.entity.Book;
import com.example.book_store.entity.CartItem;
import com.example.book_store.entity.User;
import com.example.book_store.exception.NotExistOnStockException;
import com.example.book_store.exception.NotFoundException;
import com.example.book_store.mapper.CartItemMapper;
import com.example.book_store.repository.customRepository.BookRepository;
import com.example.book_store.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;

    public void addBookToCart(Long bookId, int quantity, User currentUser) {
        Book book1 = bookRepository.findBookById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found!"));

        if (!book1.isAvailable()) {
            throw new NotExistOnStockException("No books left in stock!");
        }

        CartItem existingItem = cartRepository.findByUserAndBook(currentUser, book1)
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartRepository.save(existingItem);
        } else {
            CartItem item = new CartItem();
            item.setBook(book1);
            item.setUser(currentUser);
            item.setQuantity(quantity);
            cartRepository.save(item);
        }
    }


    public List<CartItemDto> getItems(User currentUser) {
        return cartRepository.findAllByUserId(currentUser.getId()).stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList());
    }
}
