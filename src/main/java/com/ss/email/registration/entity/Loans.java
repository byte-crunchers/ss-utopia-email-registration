package com.ss.email.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "users_id")
    private Users users;

}
