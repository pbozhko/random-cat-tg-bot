package by.bozhko.tg.bot.config.properties;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ApplicationProperties {

    @NotBlank
    private final String catsApiEndpoint;

    @NotBlank
    private final String botToken;

    @NotBlank
    private final String catsApiKey;

    @Min(500)
    @Max(10000)
    private final Integer readTimeout;

    @Min(500)
    @Max(10000)
    private final Integer connectTimeout;
}
