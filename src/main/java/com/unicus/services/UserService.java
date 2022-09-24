package com.unicus.services;

import com.unicus.dtos.requests.LoginRequest;
import com.unicus.dtos.requests.UserRequest;
import com.unicus.dtos.responses.Response;
import com.unicus.dtos.responses.UserResponse;

import com.unicus.Exceptions.UnicusException;

import java.util.List;

public interface UserService {
    Response registerUser(UserRequest request) throws UnicusException;
    UserResponse getUser(String email);
    Response deleteUser(String id);
    Response updateUser(UserRequest request);
    UserResponse login(LoginRequest loginRequest);
    List<UserResponse> getAllUsers();


}
