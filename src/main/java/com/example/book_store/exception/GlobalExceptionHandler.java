package com.example.book_store.exception;

import com.example.book_store.dto.HttpApiError;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HttpApiError> handleBadRequest(BadRequestException ex){
        log.error(ex.getMessage());
        return  ResponseEntity
                .badRequest()
                .body(HttpApiError.builder()
                        .code(400)
                        .message(ex.getMessage())
                        .build());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpApiError> handleException(Exception ex){
        log.error(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(HttpApiError.builder()
                        .code(500)
                        .message(ex.getMessage())
                        .build());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpApiError> handleUserNotFound(NotFoundException ex){
        log.info(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(HttpApiError.builder()
                        .code(404)
                        .message(ex.getMessage())
                        .build());
    }
    @ExceptionHandler(NotExistOnStockException.class)
    public ResponseEntity<HttpApiError> handleNotExistOnStockException(NotExistOnStockException ex){
        log.info(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(HttpApiError.builder()
                        .code(400)
                        .message(ex.getMessage())
                        .build()
                );
    }
}
