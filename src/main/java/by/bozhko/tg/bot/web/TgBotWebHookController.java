package by.bozhko.tg.bot.web;

import by.bozhko.tg.bot.dao.model.Account;
import by.bozhko.tg.bot.dao.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TgBotWebHookController {

    private final TelegramWebhookBot randomCatTgWebHookBot;
    private final AccountRepository accountRepository;

    @PostMapping("/api/v1/cat")
    public final ResponseEntity<BotApiMethod<?>> onUpdateReceived(@RequestBody Update update) {

        log.info("New telegram request: {}", update);

        Message message = update.getMessage();

        if (message == null) {

            message = update.getCallbackQuery().getMessage();
        }

        if (message != null) {

            User user = update.getMessage().getFrom();

            Optional<Account> account = accountRepository.findByAccountId(user.getId().longValue());

            if (account.isPresent()) {

                log.info("Account is already known");
            } else {

                Account newAccount = new Account(
                    null,
                    user.getId().longValue(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getBot(),
                    user.getUserName(),
                    user.getLanguageCode()
                );

                log.info("Account is unknown. Create new one");
                accountRepository.save(newAccount);
            }

            return new ResponseEntity<BotApiMethod<?>>(
                randomCatTgWebHookBot.onWebhookUpdateReceived(update),
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
