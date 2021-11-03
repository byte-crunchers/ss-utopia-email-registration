package com.ss.email.registration.service;


import com.ss.email.registration.dto.LoanRegistrationRequest;
import com.ss.email.registration.entity.Loans;
import com.ss.email.registration.repository.LoanRepository;

import com.ss.email.registration.security.token.ConfirmationToken;
import com.ss.email.registration.security.token.ConfirmationTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoansService  {

    private final LoanRepository loanRepository;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public LoansService( LoanRepository loanRepository, ConfirmationTokenService confirmationTokenService) {

        this.loanRepository = loanRepository;
        this.confirmationTokenService = confirmationTokenService;
    }



    public String signUpLoan(LoanRegistrationRequest loanRegistrationRequest) {
        boolean loanExists = loanRepository.findById(loanRegistrationRequest.getLoan_id()).isPresent();

        if (loanExists) {

            Loans loansPrevious = loanRepository.findById(loanRegistrationRequest.getLoan_id()).get();
            boolean isConfirmed = loansPrevious.isConfirmed();

            if (!isConfirmed) {
                String uuid = UUID.randomUUID().toString();
                //A method to save user and token in this class
                saveConfirmationToken(loansPrevious, uuid);

                return uuid;

            }
            throw new IllegalStateException(String.format("loan with id %s already sign up!", loanRegistrationRequest.getLoan_id()));
        }
        throw new IllegalStateException(String.format("loan with id %s does not exist!", loanRegistrationRequest.getLoan_id()));
    }


    private void saveConfirmationToken(Loans loans, String token) {
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), loans);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
    }

    public int enableLoan(long id) {
        return loanRepository.confirmLoan(id);

    }
}
