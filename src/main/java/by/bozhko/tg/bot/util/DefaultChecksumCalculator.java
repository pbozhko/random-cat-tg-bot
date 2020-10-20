package by.bozhko.tg.bot.util;

import lombok.RequiredArgsConstructor;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
public class DefaultChecksumCalculator implements ChecksumCalculator {

    @Override
    public String calculate(byte[] bytes) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        byte[] digest = md.digest();

        return DatatypeConverter.printHexBinary(digest);
    }
}
