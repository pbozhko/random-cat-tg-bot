package by.bozhko.tg.bot.service;

import by.bozhko.tg.bot.model.Cat;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.ExecutionException;

public interface RandomCatUrlService {

    Cat getCat() throws ExecutionException, InterruptedException, JsonProcessingException;
}
