package com.ss.email.registration.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

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

    private int user_id;
    private String account_type;
    private float balance;
    private Timestamp payment_due;
    private Timestamp due_date;
    private int limit;
    private boolean is_active = false;
}
