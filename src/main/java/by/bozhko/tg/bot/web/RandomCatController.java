package by.bozhko.tg.bot.web;

import by.bozhko.tg.bot.model.Cat;
import by.bozhko.tg.bot.service.RandomCatUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class RandomCatController {

    private final RandomCatUrlService randomCatUrlService;

    @GetMapping("/api/v1/cat")
    public final Cat getCat() throws InterruptedException, ExecutionException, IOException {

        return new Cat(randomCatUrlService.getCat().getImageUrl());
    }
}
