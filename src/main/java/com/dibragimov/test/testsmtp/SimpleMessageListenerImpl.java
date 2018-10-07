package com.dibragimov.test.testsmtp;

import com.dibragimov.test.testsmtp.dto.Message;
import com.dibragimov.test.testsmtp.telegram.TelegramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.subethamail.smtp.helper.SimpleMessageListener;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * SMTP server listener to intercept and process emails
 */
@Service
public class SimpleMessageListenerImpl implements SimpleMessageListener {

    private Logger logger = LoggerFactory.getLogger(SimpleMessageListenerImpl.class.getName());

    private TelegramService telegramService;

    private SimpleMessageListenerImpl(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean accept(String from, String recipient) {
        return true;
    }

    @Override
    public void deliver(String from, String recipient, InputStream data) {
        Session session = Session.getDefaultInstance(new Properties());
        try {
            MimeMessage mimeMessage = new MimeMessage(session, data);
            telegramService.sendMessage(new Message(
                    mimeMessage.getSubject(),
                    from,
                    recipient,
                    mimeMessage.getContent().toString()
            ));
        } catch (IOException | MessagingException e) {
            logger.info("Error processing email from " + from + " to " + recipient, e);
        }
    }
}