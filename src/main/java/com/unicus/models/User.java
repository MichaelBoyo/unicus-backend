package com.unicus.models;

import com.unicus.dtos.responses.UserResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
@Data
@Document
@Builder
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Integer day;
    private String month;
    private Integer year;
    private String aboutMe;
    private String imageUrl;
    private Set<Roles> roles;
    private String motto;

    public void addRole(Roles role) {
        if (roles == null) roles = new HashSet<>();
        roles.add(role);
    }

    public UserResponse toUserResponse() {
        return new UserResponse(id,firstName, lastName, email,phoneNumber,roles,day,month,year,aboutMe,imageUrl);
    }


}
