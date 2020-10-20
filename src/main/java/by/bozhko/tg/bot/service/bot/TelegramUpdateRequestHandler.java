package by.bozhko.tg.bot.service.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface TelegramUpdateRequestHandler {

    SendPhoto getSendPhoto(Update update) throws InterruptedException, ExecutionException, IOException;

    SendPhoto getKitSendPhoto(Update update);

    SendMessage getFirstMessage(Update update);

    SendMessage getDefaultMessage(Update update);
}
