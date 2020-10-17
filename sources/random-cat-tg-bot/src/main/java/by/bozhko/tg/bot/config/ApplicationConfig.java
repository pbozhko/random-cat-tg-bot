package by.bozhko.tg.bot.config;

import by.bozhko.tg.bot.config.properties.ApplicationProperties;
import by.bozhko.tg.bot.service.DefaultRandomCatUrlService;
import by.bozhko.tg.bot.service.RandomCatUrlService;
import by.bozhko.tg.bot.service.converter.DefaultRandomCatConverter;
import by.bozhko.tg.bot.service.converter.RandomCatConverter;
import by.bozhko.tg.bot.util.JsonRandomCatDeserializer;
import by.bozhko.tg.bot.util.RandomCatDeserializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@Validated
public class ApplicationConfig {

    @Bean
    ObjectMapper objectMapper() {

        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean
    AsyncHttpClient asyncHttpClient() {

        DefaultAsyncHttpClientConfig config = Dsl.config()
            .setReadTimeout(1000)
            .setConnectTimeout(1000)
            .setMaxRedirects(3)
            .build();

        return new DefaultAsyncHttpClient(config);
    }

    @Bean
    RandomCatDeserializer randomCatSerializer(ObjectMapper objectMapper) {

        return new JsonRandomCatDeserializer(objectMapper);
    }

    @Bean
    RandomCatConverter randomCatConverter() {

        return new DefaultRandomCatConverter();
    }

    @Bean
    RandomCatUrlService randomCatUrlService(
        AsyncHttpClient asyncHttpClient,
        RandomCatDeserializer randomCatDeserializer,
        RandomCatConverter randomCatConverter,
        ApplicationProperties applicationProperties
    ) {

        return new DefaultRandomCatUrlService(
            asyncHttpClient,
            randomCatDeserializer,
            randomCatConverter,
            applicationProperties
        );
    }
}
