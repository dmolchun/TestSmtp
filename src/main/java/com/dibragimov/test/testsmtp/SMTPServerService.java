package com.dibragimov.test.testsmtp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * SMTP server to process emails
 */

@Service
public class SMTPServerService {
    private Logger logger = LoggerFactory.getLogger(SMTPServerService.class.getName());

    private SMTPServer smtpServer;
    private SimpleMessageListenerImpl listener;

    @Value("${smtpserver.enabled}")
    private boolean enabled = false;
    @Value("${smtpserver.hostName}")
    private String hostName;
    @Value("${smtpserver.port}")
    private Integer port;

    private SMTPServerService(SimpleMessageListenerImpl listener) {
        this.listener = listener;
    }

    @PostConstruct
    public void start() {
        if (enabled) {
            smtpServer = new SMTPServer(new SimpleMessageListenerAdapter(listener));
            smtpServer.setHostName(hostName);
            smtpServer.setPort(port);
            smtpServer.start();
            logger.info("****** SMTP Server is running for domain " + smtpServer.getHostName() + " on port " + smtpServer.getPort());
        } else {
            logger.info("****** SMTP Server NOT ENABLED by settings ");
        }
    }

    @PreDestroy
    public void stop() {
        if (enabled) {
            logger.info("****** Stopping SMTP Server for domain " + smtpServer.getHostName() + " on port " + smtpServer.getPort());
            smtpServer.stop();
        }
    }
}
