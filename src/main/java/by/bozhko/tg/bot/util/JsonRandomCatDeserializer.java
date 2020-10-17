package by.bozhko.tg.bot.util;

import by.bozhko.tg.bot.model.RandomCat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JsonRandomCatDeserializer implements RandomCatDeserializer {

    private final ObjectMapper objectMapper;

    @Override
    public List<RandomCat> deserialize(String source) throws IOException {

        return objectMapper.readValue(source, new TypeReference<List<RandomCat>>() {
        });
    }
}
