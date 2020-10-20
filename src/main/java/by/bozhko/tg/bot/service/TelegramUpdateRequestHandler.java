package by.bozhko.tg.bot.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramUpdateRequestHandler {

    void handleRequest(Update update);
}
