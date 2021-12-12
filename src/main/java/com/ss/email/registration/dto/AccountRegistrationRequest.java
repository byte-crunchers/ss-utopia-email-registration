package com.ss.email.registration.dto;


public class AccountRegistrationRequest  {
    private final  String email;
    private final  String firstName;
    private final Long account_id;

    public String getEmail() {
        return email;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public AccountRegistrationRequest(String email, String firstName, Long account_id) {
        this.email = email;
        this.firstName = firstName;
        this.account_id = account_id;
    }

}
