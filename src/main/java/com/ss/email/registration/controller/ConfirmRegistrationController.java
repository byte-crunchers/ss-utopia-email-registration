package com.ss.email.registration.controller;

import com.ss.email.registration.dto.AccountRegistrationRequest;
import com.ss.email.registration.dto.LoanRegistrationRequest;
import com.ss.email.registration.dto.UserRegistrationRequest;
import com.ss.email.registration.service.AccountService;
import com.ss.email.registration.service.LoansService;
import com.ss.email.registration.service.RegistrationService;
import com.ss.email.registration.service.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Slf4j
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
        String decodeToken = Util.uuidFromBase64(token);
        return registrationService.confirmAccountToken(decodeToken);
    }

    @GetMapping(path = "confirm/card")
    public String confirmCard(@RequestParam("token") String token) {
        String decodeToken = Util.uuidFromBase64(token);
        return registrationService.confirmAccountToken(decodeToken);
    }

    @GetMapping(path = "confirm/loan")
    public String confirmLoan(@RequestParam("token") String token) {
        String decodeToken = Util.uuidFromBase64(token);
        return registrationService.confirmLoanToken(decodeToken);
    }

    @GetMapping(path = "confirm/user")
    public String confirmUser(@RequestParam("token") String token) {
        String decodeToken = Util.uuidFromBase64(token);
        return registrationService.confirmUserToken(decodeToken);
    }

    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String Test(){

        return "OK";
    }

}
