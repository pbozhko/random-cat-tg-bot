package by.bozhko.tg.bot.service.converter;

import by.bozhko.tg.bot.model.Cat;
import by.bozhko.tg.bot.model.RandomCat;

public interface RandomCatConverter {

    Cat convertToCat(RandomCat randomCat);
}
