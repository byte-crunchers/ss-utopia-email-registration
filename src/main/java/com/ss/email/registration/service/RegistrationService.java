package com.ss.email.registration.service;

import com.netflix.discovery.EurekaClient;
import com.ss.email.registration.email.EmailSender;
import com.ss.email.registration.dto.AccountRegistrationRequest;
import com.ss.email.registration.dto.LoanRegistrationRequest;
import com.ss.email.registration.dto.UserRegistrationRequest;
import com.ss.email.registration.security.EmailValidator;
import com.ss.email.registration.security.token.ConfirmationToken;
import com.ss.email.registration.security.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
public class RegistrationService {

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient discoveryClient;

    private final AccountService accountService;
    private final UserService userService;
    private final LoansService loansService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmTokenService;
    private final EmailSender emailSender;

    public RegistrationService(AccountService accountService, UserService userService, LoansService loansService, EmailValidator emailValidator, ConfirmationTokenService confirmTokenService, EmailSender emailSender) {
        this.accountService = accountService;
        this.userService = userService;
        this.loansService = loansService;
        this.emailValidator = emailValidator;
        this.confirmTokenService = confirmTokenService;
        this.emailSender = emailSender;
    }


    public String AccountConfirm(AccountRegistrationRequest accountRegistrationRequest) {
        boolean isValidEmail = emailValidator.test(accountRegistrationRequest.getEmail());
        if (isValidEmail) {
            String tokenForNewUser = accountService.signUpAccount(accountRegistrationRequest
            );

            //Since, we are running the spring boot application in localhost, we are hardcoding the
            //url of the server. We are creating a POST request with token param
            String link ="http://192.168.1.194:8090/api/v1/signup/confirm/account?token=" + Util.uuidToBase64(tokenForNewUser);
            emailSender.sendEmail( accountRegistrationRequest.getEmail(), buildEmail( accountRegistrationRequest.getFirstName(), link));
            return tokenForNewUser;
        } else {
            throw new IllegalStateException(String.format("Email %s, not valid",  accountRegistrationRequest.getEmail()));
        }
    }


    public String CardConfirm(AccountRegistrationRequest accountRegistrationRequest) {
        boolean isValidEmail = emailValidator.test(accountRegistrationRequest.getEmail());
        if (isValidEmail) {
            String tokenForNewUser = accountService.signUpAccount(accountRegistrationRequest
            );

            //Since, we are running the spring boot application in localhost, we are hardcoding the
            //url of the server. We are creating a POST request with token param
            String link = "http://192.168.1.194:8090/api/v1/signup/confirm/card?token=" + Util.uuidToBase64(tokenForNewUser);
            emailSender.sendEmail( accountRegistrationRequest.getEmail(), buildEmail( accountRegistrationRequest.getFirstName(), link));
            return tokenForNewUser;
        } else {
            throw new IllegalStateException(String.format("Email %s, not valid",  accountRegistrationRequest.getEmail()));
        }
    }


    public String LoanConfirm(LoanRegistrationRequest loanRegistrationRequest){
        boolean isValidEmail = emailValidator.test(loanRegistrationRequest.getEmail());
        if (isValidEmail) {
            String tokenForNewUser = loansService.signUpLoan(loanRegistrationRequest);

            //Since, we are running the spring boot application in localhost, we are hardcoding the
            //url of the server. We are creating a POST request with token param
            String link = "http://192.168.1.194:8090/api/v1/signup/confirm/loan?token=" + Util.uuidToBase64(tokenForNewUser);
            emailSender.sendEmail(loanRegistrationRequest.getEmail(), buildEmail(loanRegistrationRequest.getFirstName(), link));
            return tokenForNewUser;
        } else {
            throw new IllegalStateException(String.format("Email %s, not valid", loanRegistrationRequest.getEmail()));
        }
    }

    public String UserConfirm(UserRegistrationRequest userRegistrationRequest) {
        boolean isValidEmail = emailValidator.test(userRegistrationRequest.getEmail());
        if (isValidEmail) {
            String tokenForNewUser = userService.signUpUser(userRegistrationRequest);

            //Since, we are running the spring boot application in localhost, we are hardcoding the
            //url of the server. We are creating a POST request with token param
            String link = "http://192.168.1.194:8090/api/v1/signup/confirm/user?token=" + Util.uuidToBase64(tokenForNewUser);
            emailSender.sendEmail(userRegistrationRequest.getEmail(), buildEmail(userRegistrationRequest.getFirstName(), link));
            return tokenForNewUser;
        } else {
            throw new IllegalStateException(String.format("Email %s, not valid", userRegistrationRequest.getEmail()));
        }
    }

    @Transactional
    public String confirmAccountToken(String token) {
        Optional<ConfirmationToken> confirmToken = confirmTokenService.getToken(token);

        if (confirmToken.isEmpty()) {
            throw new IllegalStateException("Token not found!");
        }

        if (confirmToken.get().getConfirmedAt() != null) {
            throw new IllegalStateException("Email is already confirmed");
        }

        LocalDateTime expiresAt = confirmToken.get().getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is already expired!");
        }

        confirmTokenService.setConfirmedAt(token);
        accountService.enableAccount(confirmToken.get().getAccounts().getId());
        //Returning confirmation message if the token matches
        return "Confirmed. Thank you for using our service!";
    }



    @Transactional
    public String confirmLoanToken(String token) {
        Optional<ConfirmationToken> confirmToken = confirmTokenService.getToken(token);

        if (confirmToken.isEmpty()) {
            throw new IllegalStateException("Token not found!");
        }

        if (confirmToken.get().getConfirmedAt() != null) {
            throw new IllegalStateException("Email is already confirmed");
        }

        LocalDateTime expiresAt = confirmToken.get().getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is already expired!");
        }

        confirmTokenService.setConfirmedAt(token);

        loansService.enableLoan(confirmToken.get().getLoans().getId());

        //Returning confirmation message if the token matches
        return "Confirmed. Thank you for using our service!";
    }

    @Transactional
    public String confirmUserToken(String token) {
        Optional<ConfirmationToken> confirmToken = confirmTokenService.getToken(token);

        if (confirmToken.isEmpty()) {
            throw new IllegalStateException("Token not found!");
        }

        if (confirmToken.get().getConfirmedAt() != null) {
            throw new IllegalStateException("Email is already confirmed");
        }

        LocalDateTime expiresAt = confirmToken.get().getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is already expired!");
        }

        confirmTokenService.setConfirmedAt(token);

        userService.enableUser(confirmToken.get().getUsers().getId());

        //Returning confirmation message if the token matches
        return "Confirmed. Thank you for using our service!";
    }



    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your signup</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for choosing our service. Please click on the below link to confirm your signup: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Confirm Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}


