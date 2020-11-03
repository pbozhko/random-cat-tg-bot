package by.bozhko.tg.bot.security;

import by.bozhko.tg.bot.dao.model.User;
import by.bozhko.tg.bot.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ShaPasswordEncoder shaPasswordEncoder;
    private final StringSupport stringSupport;
    private final UserFactory userFactory;
    private final JwtService jwtService;
    private final DateGenerator dateGenerator;

    public boolean isUserValid(User user, String password) {


    }
}
