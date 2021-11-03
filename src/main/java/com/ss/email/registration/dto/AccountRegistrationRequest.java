package com.ss.email.registration.dto;

public class AccountRegistrationRequest  {
    private final String email;
    private final String firstName;
    private final int account_id;

    public String getEmail() {
        return email;
    }

    public long getAccount_id() {
        return account_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public AccountRegistrationRequest(String email, String firstName, int account_id) {
        this.email = email;
        this.firstName = firstName;
        this.account_id = account_id;
    }
}
