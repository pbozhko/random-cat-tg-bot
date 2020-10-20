package by.bozhko.tg.bot.dao.model;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class Photo {

    private final Long id;
    private final String title;
    private final Instant createdAt;
    private final String mimeType;
    private final Integer width;
    private final Integer height;
    private final byte[] content;
    private final UUID uuid;
}
