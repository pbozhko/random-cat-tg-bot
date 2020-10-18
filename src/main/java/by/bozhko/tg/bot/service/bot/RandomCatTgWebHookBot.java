package by.bozhko.tg.bot.service.bot;

import by.bozhko.tg.bot.config.properties.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class RandomCatTgWebHookBot extends TelegramWebhookBot {

    private final TelegramUpdateRequestHandler telegramUpdateRequestHandler;
    private final ApplicationProperties applicationProperties;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        try {
            execute(telegramUpdateRequestHandler.getSendPhoto(update));
        } catch (TelegramApiException | InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }

        return telegramUpdateRequestHandler.getSendMessage(update);
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {

        return applicationProperties.getBotToken();
    }

    @Override
    public String getBotPath() {
        return null;
    }
}
