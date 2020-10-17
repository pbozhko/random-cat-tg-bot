package by.bozhko.tg.bot.service;

import by.bozhko.tg.bot.config.properties.ApplicationProperties;
import by.bozhko.tg.bot.model.Cat;
import by.bozhko.tg.bot.model.RandomCat;
import by.bozhko.tg.bot.service.converter.RandomCatConverter;
import by.bozhko.tg.bot.util.RandomCatDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.asynchttpclient.util.HttpConstants;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class DefaultRandomCatUrlService implements RandomCatUrlService {

    private final AsyncHttpClient asyncHttpClient;
    private final RandomCatDeserializer randomCatDeserializer;
    private final RandomCatConverter randomCatConverter;
    private final ApplicationProperties applicationProperties;

    @Override
    public final Cat getCat() throws ExecutionException, InterruptedException, IOException {

        String responseBody = getResponse(applicationProperties.getCatsApiEndpoint()).getResponseBody();

        RandomCat randomCat = randomCatDeserializer.deserialize(responseBody).get(0);

        return randomCatConverter.convertToCat(randomCat);
    }

    private Response getResponse(String catsApiEndpoint) throws ExecutionException, InterruptedException {

        Request request = new RequestBuilder()
            .setMethod(HttpConstants.Methods.GET)
            .setUrl(catsApiEndpoint)
            .build();

        return asyncHttpClient.executeRequest(request).toCompletableFuture().get();
    }
}
