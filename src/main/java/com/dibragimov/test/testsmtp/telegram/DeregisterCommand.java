package com.dibragimov.test.testsmtp.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class DeregisterCommand extends BotCommand {
    private Logger logger = LoggerFactory.getLogger(DeregisterCommand.class.getName());

    private TelegramService telegramService;

    public DeregisterCommand(TelegramService telegramService) {
        super("deregister", "Deregister email to receive notifications");
        this.telegramService = telegramService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String response = strings != null && strings.length > 0
                ? telegramService.deregister(strings[0], chat.getId())
                : "No email to deregister";
        SendMessage message = new SendMessage()
                .setChatId(chat.getId())
                .setText(response);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            logger.error("Error sending message for chatId " + chat.getId(), e);
        }
    }
}
