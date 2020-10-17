package by.bozhko.tg.bot.service.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface TelegramUpdateRequestHandler {

    SendPhoto handle(Update update) throws InterruptedException, ExecutionException, IOException;
}