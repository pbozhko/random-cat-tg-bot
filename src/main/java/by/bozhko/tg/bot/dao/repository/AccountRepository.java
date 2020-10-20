package by.bozhko.tg.bot.dao.repository;

import by.bozhko.tg.bot.dao.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByAccountId(Long accountId);
}
