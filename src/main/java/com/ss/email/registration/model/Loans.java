package com.ss.email.registration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name="LOANS")
public class Loans {
    @Id
    private Long id;

    private Float balance, interestRate, payment_due, monthlyPayment;
    private LocalDate dueDate;
    private boolean active;
    private String loan_type;

    private boolean approved ;

    private boolean confirmed;

    @ManyToOne
    @JoinColumn(
            name = "users_id")
    private Users users;

}
