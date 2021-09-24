package com.ss.email.registration.model;

public class CardRegistrationRequest {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final long card_id;

    public CardRegistrationRequest(String email, String password, String firstName, String lastName, int card_id) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.card_id = card_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getCard_id() {
        return card_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
