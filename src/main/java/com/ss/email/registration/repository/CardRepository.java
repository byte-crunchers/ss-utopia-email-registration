package com.ss.email.registration.repository;

import com.ss.email.registration.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CardRepository extends JpaRepository<Cards, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Cards c SET c.is_active=true WHERE c.id=?1")
    int activeCard(int id);
}
