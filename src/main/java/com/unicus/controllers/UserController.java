package com.unicus.controllers;

import com.unicus.dtos.requests.UserRequest;
import com.unicus.services.UserService;
import com.unicus.Exceptions.UnicusException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserRequest request) throws UnicusException {
        return ResponseEntity.ok(userService.registerUser(request));
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
}
