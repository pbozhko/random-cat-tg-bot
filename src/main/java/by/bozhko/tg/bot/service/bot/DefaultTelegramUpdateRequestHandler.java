package by.bozhko.tg.bot.service.bot;

import by.bozhko.tg.bot.dao.model.Photo;
import by.bozhko.tg.bot.model.Cat;
import by.bozhko.tg.bot.service.RandomCatUrlService;
import by.bozhko.tg.bot.service.RandomImageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class DefaultTelegramUpdateRequestHandler implements TelegramUpdateRequestHandler {

    private final RandomCatUrlService randomCatUrlService;
    private final RandomImageService randomImageService;

    @Override
    public SendPhoto getSendPhoto(Update update) throws InterruptedException, ExecutionException, IOException {

        Cat cat = randomCatUrlService.getCat();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Хочу Кита!");
        keyboardRow.add("Хочу других котиков!");

        List<KeyboardRow> keyboardRows = List.of(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return new SendPhoto()
            .setChatId(update.getMessage().getChatId())
            .setPhoto("Это котик", new URL(cat.getPhotoUrl()).openStream())
            .setReplyMarkup(replyKeyboardMarkup);
    }

    @Override
    public SendPhoto getKitSendPhoto(Update update) {

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Хочу Кита!");
        keyboardRow.add("Хочу других котиков!");

        List<KeyboardRow> keyboardRows = List.of(keyboardRow);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        if (randomImageService.getImage().isPresent()) {

            Photo photo = randomImageService.getImage().get();

            return new SendPhoto()
                .setChatId(update.getMessage().getChatId())
                .setPhoto("Это котик", new ByteArrayInputStream(photo.getContent()))
                .setReplyMarkup(replyKeyboardMarkup);
        } else {

            return new SendPhoto()
                .setChatId(update.getMessage().getChatId())
                .setReplyMarkup(replyKeyboardMarkup);
        }
    }

    @Override
    public SendMessage getFirstMessage(Update update) {

        // KeyboardRow keyboardRow = new KeyboardRow();
        // keyboardRow.add("Хочу Кита!");
        // keyboardRow.add("Хочу других котиков!");
        //
        // List<KeyboardRow> keyboard = List.of(keyboardRow);
        //
        // ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        // replyKeyboardMarkup.setOneTimeKeyboard(true);
        // replyKeyboardMarkup.setResizeKeyboard(true);
        // replyKeyboardMarkup.setKeyboard(keyboard);

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Хочу Кита!");
        button1.setCallbackData("Хочу Кита!");

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Хочу других котиков!");
        button2.setCallbackData("Хочу других котиков!");

        List<InlineKeyboardButton> buttonsRow = List.of(button1, button2);
        List<List<InlineKeyboardButton>> keyboard = List.of(buttonsRow);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return new SendMessage()
            .setChatId(update.getMessage().getChatId())
            .setText("Привет! У меня есть много котиков. Хочешь их посмотреть?")
            .setReplyMarkup(inlineKeyboardMarkup);
    }
}
