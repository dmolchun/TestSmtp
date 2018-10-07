package com.dibragimov.test.testsmtp.db;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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
    @Fetch(FetchMode.JOIN)
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
