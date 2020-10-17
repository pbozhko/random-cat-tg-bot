package by.bozhko.tg.bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RandomCat {

    private String id;
    private Integer width;
    private Integer height;
    private String url;
}
