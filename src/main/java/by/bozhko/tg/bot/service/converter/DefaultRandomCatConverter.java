package by.bozhko.tg.bot.service.converter;

import by.bozhko.tg.bot.model.Cat;
import by.bozhko.tg.bot.model.RandomCat;

public class DefaultRandomCatConverter implements RandomCatConverter {

    @Override
    public final Cat convertToCat(RandomCat randomCat) {

        String url = randomCat.getUrl();

        return new Cat(url);
    }
}
