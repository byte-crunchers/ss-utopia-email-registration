package com.ss.email.registration.controller;

import com.ss.email.registration.model.AccountRegistrationRequest;
import com.ss.email.registration.model.LoanRegistrationRequest;
import com.ss.email.registration.model.UserRegistrationRequest;
import com.ss.email.registration.service.AccountService;
import com.ss.email.registration.service.LoansService;
import com.ss.email.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/signup")
public class ConfirmRegistrationController {
    private final AccountService accountService;
    private final LoansService loansService;
    private final RegistrationService registrationService;

    @Autowired
    public ConfirmRegistrationController(AccountService accountService, LoansService loansService, RegistrationService registrationService) {
        this.accountService = accountService;
        this.loansService = loansService;
        this.registrationService = registrationService;
    }

    @PostMapping(path = "account")
    public String accountRegister(@RequestBody AccountRegistrationRequest request) {
        return registrationService.AccountConfirm(request);

    }

    @PostMapping(path = "user")
    public String accountRegister(@RequestBody UserRegistrationRequest request) {
        return registrationService.UserConfirm(request);

    }

    @PostMapping(path = "card")
    public String cardRegister(@RequestBody AccountRegistrationRequest request) {
        return registrationService.CardConfirm(request);
        //return "registered";
    }

    @PostMapping(path = "loan")
    public String loanregister(@RequestBody LoanRegistrationRequest request) {
        return registrationService.LoanConfirm(request);
        //return "registered";
    }

    @GetMapping(path = "confirm/account")
    public String confirmAccount(@RequestParam("token") String token) {
        return registrationService.confirmAccountToken(token);
    }

    @GetMapping(path = "confirm/card")
    public String confirmCard(@RequestParam("token") String token) {
        return registrationService.confirmAccountToken(token);
    }

    @GetMapping(path = "confirm/loan")
    public String confirmLoan(@RequestParam("token") String token) {
        return registrationService.confirmLoanToken(token);
    }

    @GetMapping(path = "confirm/user")
    public String confirmUser(@RequestParam("token") String token) {
        return registrationService.confirmUserToken(token);
    }

}
