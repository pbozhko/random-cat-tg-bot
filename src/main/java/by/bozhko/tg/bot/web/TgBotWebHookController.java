package by.bozhko.tg.bot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
public class TgBotWebHookController {

    private final TelegramWebhookBot randomCatTgWebHookBot;

    @PostMapping("/api/v1/cat")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {

        return randomCatTgWebHookBot.onWebhookUpdateReceived(update);
    }
}
