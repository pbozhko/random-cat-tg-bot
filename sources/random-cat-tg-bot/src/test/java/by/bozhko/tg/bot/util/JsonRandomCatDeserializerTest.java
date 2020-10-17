package by.bozhko.tg.bot.util;

import by.bozhko.tg.bot.model.RandomCat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonRandomCatDeserializerTest {

    private final ObjectMapper objectMapper =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final JsonRandomCatDeserializer serializer = new JsonRandomCatDeserializer(objectMapper);

    @Test
    void deserialize() {

        String source = "[{\"breeds\":[],\"id\":\"MjAxMTk1Mw\",\"url\":\"https://cdn2.thecatapi.com/images/MjAxMTk1Mw" +
            ".jpg\",\"width\":450,\"height\":600}]";

        RandomCat expectedRandomCat = new RandomCat(
            "MjAxMTk1Mw",
            450,
            600,
            "https://cdn2.thecatapi.com/images/MjAxMTk1Mw.jpg"
        );

        try {
            RandomCat randomCat = serializer.deserialize(source).get(0);
            assertEquals(randomCat, expectedRandomCat);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
