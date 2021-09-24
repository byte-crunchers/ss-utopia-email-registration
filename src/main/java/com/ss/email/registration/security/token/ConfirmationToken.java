package com.ss.email.registration.security.token;

import com.ss.email.registration.model.Accounts;

import com.ss.email.registration.model.Loans;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name="CONFIRMATIONTOKEN")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            name = "accounts_id")
    private Accounts accounts;

//    @ManyToOne
//    @JoinColumn(nullable = false,
//            name = "card_id")
//    private Cards cards;

    @ManyToOne
    @JoinColumn(
            name = "loans_id")
    private Loans loans;


    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Accounts accounts,Loans loans) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.accounts = accounts;
        this.loans = loans;

    }

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Accounts accounts) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.accounts = accounts;

    }

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Loans loans) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.loans = loans;

    }

}
