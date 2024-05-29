package com.example.trainticketing.model;

public class ModifySeatRequest {
    private String email;
    private String newSeat;

    // Constructors, getters, and setters
    public ModifySeatRequest() {}

    public ModifySeatRequest(String email, String newSeat) {
        this.email = email;
        this.newSeat = newSeat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewSeat() {
        return newSeat;
    }

    public void setNewSeat(String newSeat) {
        this.newSeat = newSeat;
    }
}