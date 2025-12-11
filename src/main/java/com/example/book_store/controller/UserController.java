package com.example.book_store.controller;

import com.example.book_store.dto.UserResponseDto;
import com.example.book_store.dto.UserUpdateDto;
import com.example.book_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() throws BadRequestException {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/find-by-username")
    public ResponseEntity<?> findUserByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDto dto) throws BadRequestException {
        UserResponseDto dto1 = userService.updateUser(dto);
        return new ResponseEntity<>(dto1, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() throws BadRequestException {
        userService.deleteCurrentUser();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
