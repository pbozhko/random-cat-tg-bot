package by.bozhko.tg.bot.util;

import java.security.NoSuchAlgorithmException;

public interface ChecksumCalculator {

    String calculate(byte[] bytes) throws NoSuchAlgorithmException;
}
