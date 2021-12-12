package com.ss.email.registration.service;

import com.ss.email.registration.dto.AccountRegistrationRequest;
import com.ss.email.registration.entity.Accounts;
import com.ss.email.registration.repository.AccountRepository;

import com.ss.email.registration.security.token.ConfirmationToken;
import com.ss.email.registration.security.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountService {


    private final AccountRepository accountRepository;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public AccountService( AccountRepository accountRepository, ConfirmationTokenService confirmationTokenService) {

        this.accountRepository = accountRepository;
        this.confirmationTokenService = confirmationTokenService;
    }


    public String signUpAccount(AccountRegistrationRequest accountRegistrationRequest) {
        boolean accountExists = accountRepository.findById(accountRegistrationRequest.getAccount_id()).isPresent();

        if (accountExists) {

            Accounts accountsPrevious = accountRepository.findById(accountRegistrationRequest.getAccount_id()).get();
            boolean isConfirmed = accountsPrevious.isConfirmed();

            if (!isConfirmed) {

                String uuid = UUID.randomUUID().toString();
                //A method to save user and token in this class
                saveConfirmationToken(accountsPrevious, uuid);
                return uuid;

            }
            throw new IllegalStateException(String.format("Account with id %s already sign up!", accountRegistrationRequest.getAccount_id()));
        }

        throw new IllegalStateException(String.format("account with id %s does not exist!", accountRegistrationRequest.getAccount_id()));
    }


    private void saveConfirmationToken(Accounts accounts, String token) {
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), accounts);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
    }

    public int enableAccount(Long id) {
        return accountRepository.confirmAccount(id);

    }

}
