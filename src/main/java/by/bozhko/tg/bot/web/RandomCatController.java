package by.bozhko.tg.bot.web;

import by.bozhko.tg.bot.model.Cat;
import by.bozhko.tg.bot.service.RandomCatUrlService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class RandomCatController {

    private final RandomCatUrlService randomCatUrlService;

    @GetMapping("/api/v1/cat")
    public final Cat getRandomCat() throws ExecutionException, InterruptedException, JsonProcessingException {

        return randomCatUrlService.getCat();
    }
}
