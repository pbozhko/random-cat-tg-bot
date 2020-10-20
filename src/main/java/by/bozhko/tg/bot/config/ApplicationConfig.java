package by.bozhko.tg.bot.config;

import by.bozhko.tg.bot.config.properties.ApplicationProperties;
import by.bozhko.tg.bot.dao.repository.PhotoRepository;
import by.bozhko.tg.bot.service.DefaultRandomCatUrlService;
import by.bozhko.tg.bot.service.DefaultRandomImageService;
import by.bozhko.tg.bot.service.RandomCatUrlService;
import by.bozhko.tg.bot.service.RandomImageService;
import by.bozhko.tg.bot.service.bot.DefaultTelegramUpdateRequestHandler;
import by.bozhko.tg.bot.service.bot.RandomCatTgWebHookBot;
import by.bozhko.tg.bot.service.bot.TelegramUpdateRequestHandler;
import by.bozhko.tg.bot.service.converter.DefaultRandomCatConverter;
import by.bozhko.tg.bot.service.converter.RandomCatConverter;
import by.bozhko.tg.bot.util.DefaultUuidGenerator;
import by.bozhko.tg.bot.util.JsonRandomCatDeserializer;
import by.bozhko.tg.bot.util.RandomCatDeserializer;
import by.bozhko.tg.bot.util.UuidGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.telegram.telegrambots.bots.TelegramWebhookBot;

@Configuration
@Validated
public class ApplicationConfig {

    @Bean
    ObjectMapper objectMapper() {

        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean
    AsyncHttpClient asyncHttpClient(ApplicationProperties applicationProperties) {

        DefaultAsyncHttpClientConfig config = Dsl.config()
            .setReadTimeout(applicationProperties.getReadTimeout())
            .setConnectTimeout(applicationProperties.getConnectTimeout())
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

    @Bean
    RandomImageService randomImageService(PhotoRepository photoRepository) {

        return new DefaultRandomImageService(photoRepository);
    }

    @Bean
    TelegramUpdateRequestHandler telegramUpdateRequestHandler(
        RandomCatUrlService randomCatUrlService,
        RandomImageService randomImageService
    ) {

        return new DefaultTelegramUpdateRequestHandler(randomCatUrlService, randomImageService);
    }

    @Bean
    UuidGenerator uuidGenerator() {

        return new DefaultUuidGenerator();
    }

    @Bean
    TelegramWebhookBot randomCatTgWebHookBot(
        TelegramUpdateRequestHandler telegramUpdateRequestHandler,
        ApplicationProperties applicationProperties,
        PhotoRepository photoRepository,
        UuidGenerator uuidGenerator
    ) {

        return new RandomCatTgWebHookBot(telegramUpdateRequestHandler, applicationProperties, photoRepository,
            uuidGenerator);
    }
}
