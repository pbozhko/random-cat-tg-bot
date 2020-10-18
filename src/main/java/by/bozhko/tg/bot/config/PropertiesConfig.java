package by.bozhko.tg.bot.config;

import by.bozhko.tg.bot.config.properties.ApplicationProperties;
import by.bozhko.tg.bot.config.properties.DataSourceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@RequiredArgsConstructor
@Validated
public class PropertiesConfig {

    private final Environment environment;

    @Bean
    @Valid
    ApplicationProperties applicationProperties(DataSourceProperties dataSourceProperties) {

        return new ApplicationProperties(
            environment.getRequiredProperty("cats_api_endpoint"),
            environment.getRequiredProperty("bot_token"),
            environment.getRequiredProperty("cats_api_key"),
            Integer.valueOf(environment.getRequiredProperty("http_read_timeout")),
            Integer.valueOf(environment.getRequiredProperty("http_connect_timeout")),
            dataSourceProperties
        );
    }

    @Bean
    @Valid
    DataSourceProperties dataSourceProperties() throws URISyntaxException {

        final URI dbUri = new URI(environment.getRequiredProperty("DATABASE_URL"));
        final boolean jdbcEnableSsl = Boolean.parseBoolean(environment.getRequiredProperty("jdbc_enable_ssl"));

        final String username = dbUri.getUserInfo().split(":")[0];
        final String password = dbUri.getUserInfo().split(":")[1];

        final String host = dbUri.getHost();
        final int port = dbUri.getPort();
        final String path = dbUri.getPath();

        String jdbcUrl =
            "jdbc:postgresql://" + host + ':' + port + path + "?user=" + username + "&password=" + password;

        if (jdbcEnableSsl) {
            jdbcUrl += "&sslmode=require";
        }

        return new DataSourceProperties(
            jdbcUrl,
            Integer.parseInt(environment.getRequiredProperty("jdbc_connection_pool_size")),
            Long.parseLong(environment.getRequiredProperty("jdbc_connection_timeout"))
        );
    }
}
