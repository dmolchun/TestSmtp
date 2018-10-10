package com.dibragimov.test.testsmtp.db;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA holder for email and chatIds info
 */
@Entity
public class EmailHolder {
    @Id
    private String email;
    @ElementCollection
    private List<Long> chatIdList = new ArrayList<>();

    public EmailHolder() {
    }

    public EmailHolder(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public List<Long> getChatIdList() {
        return chatIdList;
    }
}
