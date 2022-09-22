package com.unicus.utils;

import com.unicus.Exceptions.EmailExistsException;
import com.unicus.Exceptions.PasswordMisMatchException;
import com.unicus.dtos.requests.UserRequest;
import com.unicus.models.User;

public class Validator {
    public static void validatePassword(UserRequest request) throws PasswordMisMatchException {
        if (!request.password().equals(request.repeat_password()))
            throw new PasswordMisMatchException("password mismatch");
        if (!request.password().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"))
            throw new PasswordMisMatchException("password must contain at least one digit, one uppercase," +
                    " one lowercase, one special character and must be at least 8 characters long");
    }

    public static void validateEmail(UserRequest request) throws EmailExistsException {
        if (!request.email().matches("^[A-Za-z0-9+_.-]+@(.+)$")) throw new EmailExistsException("Invalid email");
    }

    public static void validatePhoneNumber(UserRequest request) throws EmailExistsException {
        if (!request.phoneNumber().matches("^[0-9]{11}$")) throw new EmailExistsException("Invalid phone number");
    }

    public static void mapRequestToUser(UserRequest request, User user) {
        if (request.firstName() != null) user.setFirstName(request.firstName());
        if (request.lastName() != null) user.setLastName(request.lastName());
        if (request.email() != null) user.setEmail(request.email());
        if (request.phoneNumber() != null) user.setPhoneNumber(request.phoneNumber());
    }
}
