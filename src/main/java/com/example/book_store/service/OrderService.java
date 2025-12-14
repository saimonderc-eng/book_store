package com.example.book_store.service;

import com.example.book_store.entity.Book;
import com.example.book_store.entity.Order;
import com.example.book_store.exception.NotExistOnStockException;
import com.example.book_store.repository.BookRepository;
import com.example.book_store.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Transactional
    public Order create(Order order) {
        double totalPrice = 0;

        for (Book book : order.getBooks()) {
            Book book1 = bookRepository.findBookById(book.getId())
                    .orElseThrow(() -> new RuntimeException("product by id:" + book.getId() + " not found"));
            totalPrice += book1.getPrice();
        }
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        boolean existsOnStock = true;
        if (existsOnStock){
            throw  new NotExistOnStockException("book not in stock");
        }
        return order;
    }
}
