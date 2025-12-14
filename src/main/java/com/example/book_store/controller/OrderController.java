package com.example.book_store.controller;

import com.example.book_store.entity.Order;
import com.example.book_store.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    //создание заказа...
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping
    public Order create(@RequestBody Order order){
        return orderService.create(order);

    }
}
