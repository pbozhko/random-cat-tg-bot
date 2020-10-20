package by.bozhko.tg.bot.service.bot;

import by.bozhko.tg.bot.config.properties.ApplicationProperties;
import by.bozhko.tg.bot.dao.model.Photo;
import by.bozhko.tg.bot.dao.repository.PhotoRepository;
import by.bozhko.tg.bot.util.ChecksumCalculator;
import by.bozhko.tg.bot.util.UuidGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
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
    private final PhotoRepository photoRepository;
    private final UuidGenerator uuidGenerator;
    private final ChecksumCalculator checksumCalculator;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        Message message = update.getMessage();
        CallbackQuery callbackQuery = update.getCallbackQuery();

        try {
            if (message != null) {
                handleMessage(message);
            } else {
                if (callbackQuery != null) {
                    handleCallbackQuery(update);
                }
            }
        } catch (TelegramApiException | InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void handleMessage(Message message) throws TelegramApiException {

        if (message.hasPhoto()) {
            downloadPhoto(message);
        } else {
            if (message.getText().equals("/start")) {
                execute(telegramUpdateRequestHandler.getFirstMessage(message.getChatId()));
            } else {
                execute(telegramUpdateRequestHandler.getDefaultMessage(message.getChatId()));
            }
        }
    }

    private void handleCallbackQuery(Update update) throws TelegramApiException, InterruptedException,
        ExecutionException, IOException {

        CallbackQuery callbackQuery = update.getCallbackQuery();

        BotCommand botCommand = BotCommand.valueOf(callbackQuery.getData());

        if (botCommand == BotCommand.SHOW_KIT) {

            execute(telegramUpdateRequestHandler.getKitSendPhoto(callbackQuery.getMessage().getChatId()));
        } else {

            execute(telegramUpdateRequestHandler.getSendPhoto(callbackQuery.getMessage().getChatId()));
        }
    }

    private void downloadPhoto(Message message) {

        try {
            List<PhotoSize> allPhotoSizes = message.getPhoto();

            PhotoSize photoSize = allPhotoSizes.get(allPhotoSizes.size() - 1);

            try {
                GetFile getFile = new GetFile().setFileId(photoSize.getFileId());
                String filePath = execute(getFile).getFilePath();
                File tmpFile = downloadFile(filePath);

                String mimeType = Files.probeContentType(tmpFile.toPath());

                log.info("Mime Type: {}", mimeType);

                if (mimeType == null) {

                    mimeType = "image/jpeg";
                }

                Integer height = photoSize.getHeight();
                Integer width = photoSize.getWidth();

                byte[] content = readFileToByteArray(tmpFile);

                Photo photo = new Photo(
                    null,
                    "Test Image",
                    Instant.now(),
                    mimeType,
                    width,
                    height,
                    content,
                    uuidGenerator.generate(),
                    checksumCalculator.calculate(content),
                    false
                );

                photoRepository.save(photo);

                tmpFile.deleteOnExit();
            } catch (IOException | TelegramApiException ex) {

                ex.printStackTrace();
            }
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
