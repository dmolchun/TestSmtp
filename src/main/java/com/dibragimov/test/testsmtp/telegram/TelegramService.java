package com.dibragimov.test.testsmtp.telegram;

import com.dibragimov.test.testsmtp.db.EmailHolder;
import com.dibragimov.test.testsmtp.db.EmailRepository;
import com.dibragimov.test.testsmtp.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

/**
 * Service to operate with telegram API
 */
@Service
public class TelegramService {
    private Logger logger = LoggerFactory.getLogger(TelegramService.class.getName());

    @Value("${telegram.botname}")
    private String botName;
    @Value("${telegram.token}")
    private String token;

    private EmailRepository emailRepository;
    private EmailBot emailBot;

    public TelegramService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @PostConstruct
    private void init() {
        try {
            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();
            emailBot = new EmailBot(this);
            botsApi.registerBot(emailBot);

        } catch (TelegramApiRequestException e) {
            logger.error("Error initializing Telegram bot", e);
        }
    }

    /**
     * Save new connection email-chatId to repository
     *
     * @return sting with result message
     */
    public String register(String email, Long chatId) {
        EmailHolder holder;
        if (emailRepository.existsById(email)) {
            holder = emailRepository.getOneEager(email);
            if (holder.getChatIdList().contains(chatId)) {
                return "Already registered";
            }
        } else {
            holder = new EmailHolder(email);
        }
        holder.getChatIdList().add(chatId);
        emailRepository.save(holder);
        return "Successfully registered";
    }

    /**
     * Remove connection email-chatId from repository
     *
     * @return sting with result message
     */
    public String deregister(String email, Long chatId) {
        if (emailRepository.existsById(email)) {
            EmailHolder holder = emailRepository.getOneEager(email);
            if (holder.getChatIdList().contains(chatId)) {
                holder.getChatIdList().remove(chatId);
                if (holder.getChatIdList().isEmpty()) {
                    emailRepository.delete(holder);
                } else {
                    emailRepository.save(holder);
                }
                return "Successfully deregistered";
            }
        }
        return "Not registered";
    }

    public void sendMessage(Message message) {
        if (emailRepository.existsById(message.getTo())) {
            emailRepository.getOneEager(message.getTo()).getChatIdList().forEach(chatId -> {
                SendMessage sendMessage = new SendMessage()
                        .setChatId(chatId)
                        .setText(message.toTelegramString());
                try {
                    emailBot.execute(sendMessage);
                } catch (TelegramApiException e) {
                    logger.error("Error sending message for chatId " + chatId, e);
                }
            });
        }
    }

    public String getToken() {
        return token;
    }

    public String getBotName() {
        return botName;
    }
}
