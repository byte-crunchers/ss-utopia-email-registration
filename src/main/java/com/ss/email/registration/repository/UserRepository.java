package com.ss.email.registration.repository;

import com.ss.email.registration.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Users, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.confirmed=true WHERE u.id=?1")
    int confirmUser(long id);

    Optional<Users> findByEmail(String email);
}

