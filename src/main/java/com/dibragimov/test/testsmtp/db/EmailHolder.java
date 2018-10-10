package com.dibragimov.test.testsmtp.db;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

/**
 * JPA holder for email and chatIds info
 */
@Entity
public class EmailHolder {
    @Id
    private String email;
    @ElementCollection
    private Set<Long> chatIdList = new HashSet<>();

    public EmailHolder() {
    }

    public EmailHolder(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Set<Long> getChatIdList() {
        return chatIdList;
    }
}
