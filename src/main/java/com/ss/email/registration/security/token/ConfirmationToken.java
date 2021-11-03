package com.ss.email.registration.security.token;

import com.ss.email.registration.entity.Accounts;

import com.ss.email.registration.entity.Loans;
import com.ss.email.registration.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name="CONFIRMATION")
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
            name = "account")
    private Accounts accounts;

    @ManyToOne
    @JoinColumn(
            name = "loan")
    private Loans loans;


    @ManyToOne
    @JoinColumn(
            name = "user")
    private Users users;


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

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Users users) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.users = users;

    }

}
