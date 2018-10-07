package com.dibragimov.test.testsmtp.dto;

public class Message {
    private String subject;
    private String from;
    private String to;
    private String body;

    public Message(String subject, String from, String to, String body) {
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBody() {
        return body;
    }


    public String toTelegramString() {
        return "Subject = " + subject + "\n" +
                "From = " + from + "\n" +
                "To = " + to + "\n" +
                "Body: \n" + body + "";
    }
}
