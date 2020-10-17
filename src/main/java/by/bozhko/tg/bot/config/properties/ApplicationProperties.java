package by.bozhko.tg.bot.config.properties;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ApplicationProperties {

    @NotBlank
    private final String catsApiEndpoint;
}