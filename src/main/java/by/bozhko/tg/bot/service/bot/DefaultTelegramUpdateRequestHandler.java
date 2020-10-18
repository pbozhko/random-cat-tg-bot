package by.bozhko.tg.bot.service.bot;

import by.bozhko.tg.bot.model.Cat;
import by.bozhko.tg.bot.service.RandomCatUrlService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class DefaultTelegramUpdateRequestHandler implements TelegramUpdateRequestHandler {

    private final RandomCatUrlService randomCatUrlService;

    @Override
    public SendPhoto getSendPhoto(Update update) throws InterruptedException, ExecutionException, IOException {

        Cat cat = randomCatUrlService.getCat();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("I need more cats!");

        List<KeyboardRow> keyboardRows = List.of(keyboardRow);

        return new SendPhoto()
            .setChatId(update.getMessage().getChatId())
            .setPhoto("It's a cat!", new URL(cat.getImageUrl()).openStream())
            .setReplyMarkup(new ReplyKeyboardMarkup().setKeyboard(keyboardRows));
    }
}
