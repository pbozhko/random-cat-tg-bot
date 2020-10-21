package by.bozhko.tg.bot.management.web;

import by.bozhko.tg.bot.dao.model.Account;
import by.bozhko.tg.bot.dao.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountRepository accountRepository;

    @GetMapping("/api/management/v1/accounts")
    List<Account> getAll() {

        List<Account> allAccounts = new ArrayList<>();
        accountRepository.findAll().iterator().forEachRemaining(allAccounts::add);

        return allAccounts;
    }
}
