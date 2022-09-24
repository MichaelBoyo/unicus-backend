package com.unicus.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }

    @Test
    void registerUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void login() {
    }

    @Test
    void getAllUsers() {
    }
}