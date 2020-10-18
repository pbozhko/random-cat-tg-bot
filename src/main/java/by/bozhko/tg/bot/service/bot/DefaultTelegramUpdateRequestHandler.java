package by.bozhko.tg.bot.service.bot;

import by.bozhko.tg.bot.model.Cat;
import by.bozhko.tg.bot.service.RandomCatUrlService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

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

        return new SendPhoto()
            .setChatId(update.getMessage().getChatId())
            .setPhoto("It's a cat!", new URL(cat.getImageUrl()).openStream());
    }

    @Override
    public SendMessage getSendMessage(Update update) {

        InlineKeyboardButton button = new InlineKeyboardButton()
            .setText("Show me cats!");

        List<InlineKeyboardButton> buttonsRow = List.of(button);

        List<List<InlineKeyboardButton>> keyboard = List.of(buttonsRow);

        return new SendMessage()
            .setReplyMarkup(null)
            .setChatId(update.getMessage().getChatId())
            .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(keyboard));
    }
}
