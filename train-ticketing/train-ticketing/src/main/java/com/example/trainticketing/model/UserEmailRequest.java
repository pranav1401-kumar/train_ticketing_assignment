package com.example.trainticketing.model;

public class UserEmailRequest {
    private String email;

    // Constructors, getters, and setters
    public UserEmailRequest() {}

    public UserEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
