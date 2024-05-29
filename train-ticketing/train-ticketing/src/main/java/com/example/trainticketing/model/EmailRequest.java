package com.example.trainticketing.model;

public class EmailRequest {
    private String email;

    // Constructors
    public EmailRequest() {}

    public EmailRequest(String email) {
        this.email = email;
    }

    // Getter and Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
