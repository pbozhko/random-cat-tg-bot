package by.bozhko.tg.bot.web;

import by.bozhko.tg.bot.service.TelegramUpdateRequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TgBotWebHookController {

    private final TelegramUpdateRequestHandler telegramUpdateRequestHandler;

    @PostMapping("/api/v1/cat")
    public void onUpdateReceived(@RequestBody Update update) {

        log.info("New telegram request: {}", update);
        telegramUpdateRequestHandler.handleRequest(update);
    }
}
