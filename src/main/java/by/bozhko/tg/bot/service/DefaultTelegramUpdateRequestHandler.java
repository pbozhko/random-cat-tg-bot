package by.bozhko.tg.bot.service;

import by.bozhko.tg.bot.dao.model.Account;
import by.bozhko.tg.bot.dao.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class DefaultTelegramUpdateRequestHandler implements TelegramUpdateRequestHandler {

    private final TelegramWebhookBot randomCatTgWebHookBot;
    private final AccountRepository accountRepository;

    @Override
    @Async
    public void handleRequest(Update update) {

        User user;
        Long chatId;

        Message message = update.getMessage();

        if (message == null) {

            user = update.getCallbackQuery().getFrom();
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else {

            user = message.getFrom();
            chatId = message.getChatId();
        }

        Optional<Account> account = accountRepository.findByAccountId(user.getId().longValue());

        if (account.isPresent()) {

            log.info("Account is already known");

            // TODO disable it when all users will update chat ids
            Account updatedAccount = account.get();
            updatedAccount.setChatId(chatId);

            accountRepository.save(updatedAccount);
        } else {

            Account newAccount = new Account(
                null,
                user.getId().longValue(),
                user.getFirstName(),
                user.getLastName(),
                user.getBot(),
                user.getUserName(),
                user.getLanguageCode(),
                chatId
            );

            log.info("Account is unknown. Create new one");
            accountRepository.save(newAccount);
        }

        randomCatTgWebHookBot.onWebhookUpdateReceived(update);
    }
}
