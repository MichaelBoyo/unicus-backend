package com.unicus.services;

import com.unicus.Exceptions.UnicusException;
import com.unicus.dtos.requests.LoginRequest;
import com.unicus.dtos.requests.UserRequest;
import com.unicus.dtos.responses.UserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() throws UnicusException {
        userService.registerUser(
                new UserRequest(
                        "testfirstname",
                        "testLastname",
                        "testemail@test.test","09045543945",
                        "TestPassword123#","TestPassword123#",
                        9,
                        "September",
                        1999,
                        "test about me",
                        "testurl"
                )
        );
    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }

    @Test
    void registerUser() {
        assertNotEquals(Collections.emptyList(),userService.getAllUsers());
    }

    @Test
    void getUser() {
        UserResponse userResponse = userService.getUser("testemail@test.test");
        assertNotNull(userResponse);
    }

    @Test
    void deleteUser() {
        UserResponse userResponse = userService.getUser("testemail@test.test");
        userService.deleteUser(userResponse.id());
    }

    @Test
    void updateUser() {
        userService.updateUser(new UserRequest("testemail@test.test","updatedFirstName"));
        UserResponse userResponse = userService.getUser("testemail@test.test");
        assertEquals("updatedFirstName", userResponse.firstName());
    }

    @Test
    void login() {
       UserResponse userResponse =
               userService.login(new LoginRequest("testemail@test.test","TestPassword123#"));
       assertNotNull(userResponse);
    }

    @Test
    void getAllUsers() {
        assertEquals(1,userService.getAllUsers().size());
    }
}