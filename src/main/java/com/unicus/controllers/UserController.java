package com.unicus.controllers;

import com.unicus.dtos.requests.LoginRequest;
import com.unicus.dtos.requests.UserRequest;
import com.unicus.services.UserService;
import com.unicus.Exceptions.UnicusException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserRequest request) throws UnicusException {
        log.info("registering");
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws UnicusException {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUser(email));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PatchMapping
    public ResponseEntity<?> updateUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
