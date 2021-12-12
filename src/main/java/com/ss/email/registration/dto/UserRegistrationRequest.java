package com.ss.email.registration.dto;


public class UserRegistrationRequest {
    private final  String email;
    private final String firstName;
    private final long user_id;

    public UserRegistrationRequest(String email, String firstName, long user_id) {
        this.email = email;
        this.firstName = firstName;
        this.user_id = user_id;
    }



    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public long getUser_id() {
        return user_id;
    }
}
