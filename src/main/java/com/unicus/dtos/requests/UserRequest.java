package com.unicus.dtos.requests;

public
record
UserRequest(String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String password,
            String repeat_password) {

}
