package by.bozhko.tg.bot.web;

import by.bozhko.tg.bot.service.TelegramUpdateRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
public class TgBotWebHookController {

    private final TelegramUpdateRequestHandler telegramUpdateRequestHandler;

    @PostMapping("/api/v1/cat")
    public void onUpdateReceived(@RequestBody Update update) {

        telegramUpdateRequestHandler.handleRequest(update);
    }
}
