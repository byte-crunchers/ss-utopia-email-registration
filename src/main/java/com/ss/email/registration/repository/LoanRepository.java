package com.ss.email.registration.repository;

import com.ss.email.registration.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Loans l SET l.confirmed=true WHERE l.id=?1")
    int confirmLoan(long id);
}
