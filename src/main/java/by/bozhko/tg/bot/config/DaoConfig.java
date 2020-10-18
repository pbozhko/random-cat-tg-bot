package by.bozhko.tg.bot.config;

import by.bozhko.tg.bot.dao.ImageDao;
import by.bozhko.tg.bot.dao.ImageMapper;
import by.bozhko.tg.bot.dao.JdbcImageDao;
import by.bozhko.tg.bot.dao.model.Image;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class DaoConfig {

    @Bean
    RowMapper<Image> imageRowMapper() {

        return new ImageMapper();
    }

    @Bean
    ImageDao imageDao(JdbcTemplate jdbcTemplate, RowMapper<Image> imageRowMapper) {

        return new JdbcImageDao(jdbcTemplate, imageRowMapper);
    }
}
