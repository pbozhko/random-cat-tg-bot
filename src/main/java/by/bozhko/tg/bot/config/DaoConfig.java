package by.bozhko.tg.bot.config;

import by.bozhko.tg.bot.dao.JdbcPhotoDao;
import by.bozhko.tg.bot.dao.PhotoDao;
import by.bozhko.tg.bot.dao.PhotoMapper;
import by.bozhko.tg.bot.dao.model.Photo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class DaoConfig {

    @Bean
    RowMapper<Photo> photoRowMapper() {

        return new PhotoMapper();
    }

    @Bean
    PhotoDao photoDao(JdbcTemplate jdbcTemplate, RowMapper<Photo> photoRowMapper) {

        return new JdbcPhotoDao(jdbcTemplate, photoRowMapper);
    }
}
