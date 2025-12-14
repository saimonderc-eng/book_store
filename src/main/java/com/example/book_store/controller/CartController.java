package com.example.book_store.controller;
import com.example.book_store.dto.CartItemDto;
import com.example.book_store.entity.CartItem;
import com.example.book_store.entity.User;
import com.example.book_store.service.CartService;
import com.example.book_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    //добавление товара в корзину
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestParam Long bookId, @RequestParam int quantity) throws BadRequestException {
        User currentUser = userService.getCurrentUser();
        cartService.addBookToCart(bookId, quantity, currentUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getCartItems() throws BadRequestException {
        User currentUser = userService.getCurrentUser();
        List<CartItemDto> items = cartService.getItems(currentUser);
        return ResponseEntity.ok(items);
    }

    
}
