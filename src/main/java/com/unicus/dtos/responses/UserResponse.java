package com.unicus.dtos.responses;

import com.unicus.models.Roles;

import java.util.Set;

public record UserResponse(String id,String firstName, String lastName, String email, String phoneNumber, Set<Roles> roles) {

}
