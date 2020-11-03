package by.bozhko.tg.bot.security;

import by.bozhko.tg.bot.dao.model.User;
import by.bozhko.tg.bot.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthProviderService implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UsernamePasswordAuthenticationTokenFactory usernamePasswordAuthenticationTokenFactory;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> user = userRepository.findByUsername(login);

        if (user.isPresent() && userService.isUserValid(user.get(), password)) {

            return usernamePasswordAuthenticationTokenFactory.create(user.get());
        }

        throw new UsernameNotFoundException("Неверные логин или пароль");
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
