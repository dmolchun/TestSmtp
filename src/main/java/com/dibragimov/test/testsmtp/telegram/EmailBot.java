package com.dibragimov.test.testsmtp.telegram;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class EmailBot extends TelegramLongPollingCommandBot {

    private TelegramService telegramService;

    public EmailBot(TelegramService telegramService) {
        super(telegramService.getBotName());
        this.telegramService = telegramService;
        register(new RegisterCommand(telegramService));
        register(new DeregisterCommand(telegramService));
    }

    @Override
    public String getBotToken() {
        return telegramService.getToken();
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        //doNothing
    }
}
