package com.ss.email.registration.repository;

import com.ss.email.registration.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Accounts a SET a.confirmed=true WHERE a.id=?1")
    int confirmAccount(Long id);
}
