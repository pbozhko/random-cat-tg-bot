package by.bozhko.tg.bot.service.bot;

import by.bozhko.tg.bot.model.Cat;
import by.bozhko.tg.bot.service.RandomCatUrlService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class DefaultTelegramUpdateRequestHandler implements TelegramUpdateRequestHandler {

    private final RandomCatUrlService randomCatUrlService;

    @Override
    public SendPhoto handle(Update update) throws InterruptedException, ExecutionException, IOException {

        Cat cat = randomCatUrlService.getCat();

        return new SendPhoto()
            .setChatId(update.getMessage().getChatId())
            .setPhoto("It's a cat!", new URL(cat.getImageUrl()).openStream());
    }
}
