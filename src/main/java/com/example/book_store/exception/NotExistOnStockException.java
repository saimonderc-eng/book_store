package com.example.book_store.exception;

public class NotExistOnStockException extends RuntimeException {
    public NotExistOnStockException(String message) {
        super(message);
    }
}
