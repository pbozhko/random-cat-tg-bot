package by.bozhko.tg.bot.util;

public class SystemCurrentTimeSupplier implements CurrentTimeSupplier {

    @Override
    public Long getCurrentTimeUtc() {

        return System.currentTimeMillis();
    }
}
