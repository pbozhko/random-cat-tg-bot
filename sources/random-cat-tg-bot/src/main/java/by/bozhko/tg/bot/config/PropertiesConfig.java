package by.bozhko.tg.bot.config;

import by.bozhko.tg.bot.config.properties.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Configuration
@RequiredArgsConstructor
@Validated
public class PropertiesConfig {

    private final Environment environment;

    @Bean
    @Valid
    ApplicationProperties applicationProperties() {

        return new ApplicationProperties(
            environment.getProperty("cats_api_endpoint")
        );
    }
}
