package by.bozhko.tg.bot.service.bot;

import by.bozhko.tg.bot.config.properties.ApplicationProperties;
import by.bozhko.tg.bot.dao.ImageDao;
import by.bozhko.tg.bot.dao.model.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

@RequiredArgsConstructor
@Slf4j
public class RandomCatTgWebHookBot extends TelegramWebhookBot {

    private final TelegramUpdateRequestHandler telegramUpdateRequestHandler;
    private final ApplicationProperties applicationProperties;
    private final ImageDao imageDao;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        log.info(update.toString());

        try {
            Message message = update.getMessage();

            if (message.hasPhoto()) {

                downloadPhoto(update);
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

    private void downloadPhoto(Update update) {

        try {

            final List<String> allowedMimeTypes = List.of("image/bmp", "image/gif", "image/jpeg", "image/png");

            List<PhotoSize> allPhotoSizes = update.getMessage().getPhoto();

            PhotoSize photoSize = allPhotoSizes.get(allPhotoSizes.size() - 1);

            try {
                GetFile getFile = new GetFile().setFileId(photoSize.getFileId());
                String filePath = execute(getFile).getFilePath();
                File tmpFile = downloadFile(filePath);

                String mimeType = Files.probeContentType(tmpFile.toPath());

                log.info("Mime Type: {}", mimeType);

                if (allowedMimeTypes.contains(mimeType)) {

                    Integer height = photoSize.getHeight();
                    Integer width = photoSize.getWidth();

                    Image image = new Image(
                        null,
                        "Test Image",
                        Instant.now(),
                        mimeType,
                        width,
                        height,
                        readFileToByteArray(tmpFile)
                    );

                    imageDao.save(image);
                }

                tmpFile.deleteOnExit();
            } catch (IOException | TelegramApiException ex) {

                ex.printStackTrace();
            }

            execute(telegramUpdateRequestHandler.getSendPhoto(update));
        } catch (Exception ex) {

            ex.printStackTrace();
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
