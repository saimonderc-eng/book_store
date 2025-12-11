package com.example.book_store.controller;

import com.example.book_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String welcome() {
        return "Welcome, Admin!";
    }

    @PutMapping("/assign-role")
    public ResponseEntity<?> assignRole(@RequestParam Long userId, @RequestParam Long roleId) throws BadRequestException {
        userService.assignRole(userId, roleId);
        return new ResponseEntity<>("role assigned!", HttpStatus.ACCEPTED);
    }

    @PutMapping("/unassign-role")
    public ResponseEntity<?> unassignRole(@RequestParam Long userId, @RequestParam Long roleId) throws BadRequestException {
        userService.unassignRole(userId, roleId);
        return new ResponseEntity<>("role unassigned!", HttpStatus.ACCEPTED);
    }

    @PutMapping("/ban")
    public ResponseEntity<?> banUser(@RequestParam Long userId) throws BadRequestException {
        userService.banUser(userId);
        return ResponseEntity.ok("user banned successfully");
    }
}
