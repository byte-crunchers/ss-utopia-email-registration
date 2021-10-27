package com.ss.email.registration.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="ACCOUNTS")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account_type;

    private float balance;

    private float debt_interest;

    private float payment_due;

    private Date due_date;

    private int limit;

    private boolean active ;

    private boolean approved ;

    private boolean confirmed;


    @ManyToOne
    @JoinColumn(
            name = "users_id")
    private Users users;

}
