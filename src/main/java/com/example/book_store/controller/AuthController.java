package com.example.book_store.controller;

import com.example.book_store.dto.*;
import com.example.book_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateDto dto) throws BadRequestException {
        AuthResponse authResponse = userService.register(dto);
        return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) throws BadRequestException {
        AuthResponse authResponse = userService.login(request);
        return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto dto) throws BadRequestException {
        userService.changePassword(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDto dto) throws BadRequestException {
        AuthResponse response = userService.refreshToken(dto);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}