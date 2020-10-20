package by.bozhko.tg.bot.service.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface TelegramBotPhotoService {

    SendPhoto getSendPhoto(Long chatId) throws InterruptedException, ExecutionException, IOException;

    SendPhoto getKitSendPhoto(Long chatId);

    SendMessage getFirstMessage(Long chatId);

    SendMessage getDefaultMessage(Long chatId);
}
