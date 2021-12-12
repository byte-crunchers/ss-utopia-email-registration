package com.ss.email.registration.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="ACCOUNTS")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String account_type;

    private float balance;

    private float payment_due;

    private Date due_date;

    private float credit_limit;

    private float debt_interest;

    private boolean active ;

    private boolean approved ;

    private boolean confirmed;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "users_id")
    private Users users;

}
