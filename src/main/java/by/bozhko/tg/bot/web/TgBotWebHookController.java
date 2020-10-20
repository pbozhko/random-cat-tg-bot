package by.bozhko.tg.bot.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TgBotWebHookController {

    private final TelegramWebhookBot randomCatTgWebHookBot;

    @PostMapping("/api/v1/cat")
    public final BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {

        log.info("New telegram request: {}", update);
        return randomCatTgWebHookBot.onWebhookUpdateReceived(update);
    }
}
