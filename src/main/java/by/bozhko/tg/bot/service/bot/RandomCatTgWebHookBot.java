package by.bozhko.tg.bot.service.bot;

import by.bozhko.tg.bot.config.properties.ApplicationProperties;
import by.bozhko.tg.bot.dao.ImageDao;
import by.bozhko.tg.bot.dao.model.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Slf4j
public class RandomCatTgWebHookBot extends TelegramWebhookBot {

    private final TelegramUpdateRequestHandler telegramUpdateRequestHandler;
    private final ApplicationProperties applicationProperties;
    private final ImageDao imageDao;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        try {

            Message message = update.getMessage();

            if (message.getDocument() != null) {

                downloadDocument(message);
            } else {

                String messageText = message.getText();

                if (messageText != null) {

                    if (messageText.equals("/start")) {

                        execute(telegramUpdateRequestHandler.getFirstMessage(update));
                    } else {

                        execute(telegramUpdateRequestHandler.getSendPhoto(update));
                    }
                }
            }
        } catch (TelegramApiException | InterruptedException | ExecutionException | IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    private void downloadDocument(Message message) throws TelegramApiException, IOException {

        log.info(message.getDocument().toString());
        log.info(message.getPhoto().toString());

        final Document document = message.getDocument();
        final List<String> allowedMimeTypes = List.of("image/bmp", "image/gif", "image/jpeg", "image/png");
        String mimeType = document.getMimeType();

        if (allowedMimeTypes.contains(mimeType)) {

            File file = downloadFile(document.getFileId());

            PhotoSize photoSize = document.getThumb();

            Integer height = photoSize.getHeight();
            Integer width = photoSize.getWidth();

            Image image = new Image(
                null,
                "Test Image",
                Instant.now(),
                mimeType,
                width,
                height,
                FileUtils.readFileToByteArray(file)
            );

            imageDao.save(image);

            file.deleteOnExit();
        }
    }

    @Override
    public String getBotUsername() {

        return null;
    }

    @Override
    public String getBotToken() {

        return applicationProperties.getBotToken();
    }

    @Override
    public String getBotPath() {

        return null;
    }
}
