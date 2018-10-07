package com.dibragimov.test.testsmtp.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmailRepository extends JpaRepository<EmailHolder, String> {
    @Query("SELECT e FROM EmailHolder e JOIN FETCH e.chatIdList WHERE e.email = :email")
    EmailHolder getOneEager(@Param("email") String email);
}
