package com.unicus.dtos.requests;

public
record
UserRequest(String firstName, String lastName,
            String email, String phoneNumber,
            String password, String repeat_password,
            Integer day, String month,
            Integer year, String aboutMe,
            String imageUrl) {
    public UserRequest(String email,String firstName){
        this(firstName,null,email,null,null,null,null,null,null,null,null);
    }

}
