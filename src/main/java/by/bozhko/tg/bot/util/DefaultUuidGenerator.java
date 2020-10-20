package by.bozhko.tg.bot.util;

import java.util.UUID;

public class DefaultUuidGenerator implements UuidGenerator {

    @Override
    public UUID generate() {

        return UUID.randomUUID();
    }
}
