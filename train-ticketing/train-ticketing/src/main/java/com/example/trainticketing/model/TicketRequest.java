package com.example.trainticketing.model;

public class TicketRequest {
    private String from;
    private String to;
    private User user;
    private double pricePaid; // New field for price paid

    // Constructors, getters, and setters
    public TicketRequest() {}

    public TicketRequest(String from, String to, User user, double pricePaid) {
        this.from = from;
        this.to = to;
        this.user = user;
        this.pricePaid = pricePaid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }
}
