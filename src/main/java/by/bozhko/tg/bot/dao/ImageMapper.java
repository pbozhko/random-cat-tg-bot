package by.bozhko.tg.bot.dao;

import by.bozhko.tg.bot.dao.model.Image;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageMapper implements RowMapper<Image> {

    @Override
    public Image mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return new Image(
            resultSet.getLong("id"),
            resultSet.getString("title"),
            resultSet.getTimestamp("created_at").toInstant(),
            resultSet.getString("mime_type"),
            resultSet.getInt("width"),
            resultSet.getInt("height"),
            resultSet.getBytes("content")
        );
    }
}
