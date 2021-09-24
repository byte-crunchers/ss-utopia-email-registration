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

@Table(name="LOANS")
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int user_id;
    private float interest_rate;
    private float balance;
    private Timestamp payment_due;
    private Timestamp due_date;
    private boolean is_active = false;

}
