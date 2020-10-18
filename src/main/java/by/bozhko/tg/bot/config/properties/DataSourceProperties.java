package by.bozhko.tg.bot.config.properties;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class DataSourceProperties {

    @NotBlank
    private final String url;

    @Min(1)
    @Max(10)
    private final Integer connectionPoolSize;

    @Min(500)
    @Max(10000)
    private final Long connectionTimeout;
}
