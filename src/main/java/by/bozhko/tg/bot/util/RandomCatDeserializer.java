package by.bozhko.tg.bot.util;

import by.bozhko.tg.bot.model.RandomCat;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface RandomCatDeserializer {

    List<RandomCat> deserialize(String source) throws IOException;
}
