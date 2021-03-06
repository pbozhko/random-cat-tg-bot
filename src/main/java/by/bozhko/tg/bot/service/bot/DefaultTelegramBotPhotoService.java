package by.bozhko.tg.bot.service.bot;

import by.bozhko.tg.bot.dao.model.Photo;
import by.bozhko.tg.bot.model.Cat;
import by.bozhko.tg.bot.service.RandomCatUrlService;
import by.bozhko.tg.bot.service.RandomImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class DefaultTelegramBotPhotoService implements TelegramBotPhotoService {

    private final RandomCatUrlService randomCatUrlService;
    private final RandomImageService randomImageService;

    @Override
    public SendPhoto getSendPhoto(Long chatId) throws InterruptedException, ExecutionException, IOException {

        Cat cat = randomCatUrlService.getCat();

        byte[] imageBytes;

        try (InputStream is = new URL(cat.getPhotoUrl()).openStream()) {

            imageBytes = IOUtils.toByteArray(is);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }

        try (InputStream is = new ByteArrayInputStream(imageBytes)) {

            return new SendPhoto()
                .setChatId(chatId)
                .setPhoto("Это котик", is)
                .setReplyMarkup(buildInlineKeyboardMarkup());
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SendPhoto getKitSendPhoto(Long chatId) {

        if (randomImageService.getImage().isPresent()) {

            Photo photo = randomImageService.getImage().get();

            try (InputStream is = new ByteArrayInputStream(photo.getContent())) {

                return new SendPhoto()
                    .setChatId(chatId)
                    .setPhoto("Это котик", is)
                    .setReplyMarkup(buildInlineKeyboardMarkup());
            } catch (IOException e) {

                e.printStackTrace();
                return null;
            }
        } else {

            return new SendPhoto()
                .setChatId(chatId)
                .setReplyMarkup(buildInlineKeyboardMarkup());
        }
    }

    @Override
    public SendMessage getFirstMessage(Long chatId) {

        return new SendMessage()
            .setChatId(chatId)
            .setText("Привет! У меня есть много котиков. Хочешь их посмотреть?")
            .setReplyMarkup(buildInlineKeyboardMarkup());
    }

    @Override
    public SendMessage getDefaultMessage(Long chatId) {

        return new SendMessage()
            .setChatId(chatId)
            .setText("Каких котиков ты хочешь увидеть?")
            .setReplyMarkup(buildInlineKeyboardMarkup());
    }

    private InlineKeyboardMarkup buildInlineKeyboardMarkup() {

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Хочу Кита!");
        button1.setCallbackData(BotCommand.SHOW_KIT.name());

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Хочу других котиков!");
        button2.setCallbackData(BotCommand.SHOW_OTHER_CATS.name());

        List<InlineKeyboardButton> buttonsRow = List.of(button1, button2);
        List<List<InlineKeyboardButton>> keyboard = List.of(buttonsRow);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }
}
