package com.unicus.services;

import com.unicus.dtos.requests.UserRequest;
import com.unicus.dtos.responses.Response;
import com.unicus.dtos.responses.UserResponse;

import com.unicus.Exceptions.UnicusException;

public interface UserService {
    Response registerUser(UserRequest request) throws UnicusException;
    UserResponse getUser(String email);
    Response deleteUser(String email);
    Response updateUser(UserRequest request);


}
