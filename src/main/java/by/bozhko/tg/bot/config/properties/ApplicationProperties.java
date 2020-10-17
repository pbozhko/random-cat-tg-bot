package by.bozhko.tg.bot.config.properties;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ApplicationProperties {

    @NotBlank
    private final String catsApiEndpoint;

    @NotBlank
    private final String botToken;

    @NotBlank
    private final String catsApiKey;

    @Size(min = 500, max = 10000)
    private final Integer readTimeout;

    @Size(min = 500, max = 10000)
    private final Integer connectTimeout;
}
