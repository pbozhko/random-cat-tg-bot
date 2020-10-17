package by.bozhko.tg.bot.web;

import by.bozhko.tg.bot.model.RandomCat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomCatController {

    @GetMapping("/api/v1/cat")
    public RandomCat getRandomCat() {

        return new RandomCat("http://example.com");
    }
}
