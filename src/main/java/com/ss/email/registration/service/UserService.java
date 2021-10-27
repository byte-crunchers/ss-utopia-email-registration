package com.ss.email.registration.service;

import com.ss.email.registration.model.LoanRegistrationRequest;
import com.ss.email.registration.model.Loans;
import com.ss.email.registration.model.UserRegistrationRequest;
import com.ss.email.registration.model.Users;
import com.ss.email.registration.repository.UserRepository;
import com.ss.email.registration.security.PasswordEncoder;
import com.ss.email.registration.security.token.ConfirmationToken;
import com.ss.email.registration.security.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));

    }

    public String signUpUser(UserRegistrationRequest userRegistrationRequest) {
        boolean userExists = userRepository.findById(userRegistrationRequest.getUser_id()).isPresent();

        if (userExists) {

            Users usersPrevious = userRepository.findById(userRegistrationRequest.getUser_id()).get();
            boolean isConfirmed = usersPrevious.isConfirmed();

            if (!isConfirmed) {
                String token = UUID.randomUUID().toString();

                //A method to save user and token in this class
                saveConfirmationToken(usersPrevious, token);

                return token;

            }
            throw new IllegalStateException(String.format("user with id %s already sign up!", userRegistrationRequest.getUser_id()));
        }

        return UUID.randomUUID().toString();
    }

    private void saveConfirmationToken(Users users, String token) {
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), users);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
    }

    public int enableUser(long id) {
        return userRepository.confirmUser(id);

    }

}
