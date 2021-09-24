package com.ss.email.registration.model;

public class LoanRegistrationRequest {

    private final String email;
    private final String firstName;
    private final long loan_id;

    public LoanRegistrationRequest(String email, String firstName, int loan_id) {
        this.email = email;
        this.firstName = firstName;
        this.loan_id = loan_id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public long getLoan_id() {
        return loan_id;
    }
}
