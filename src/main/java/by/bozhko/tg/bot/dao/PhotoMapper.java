package by.bozhko.tg.bot.dao;

import by.bozhko.tg.bot.dao.model.Photo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PhotoMapper implements RowMapper<Photo> {

    @Override
    public Photo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return new Photo(
            resultSet.getLong("id"),
            resultSet.getString("title"),
            resultSet.getTimestamp("created_at").toInstant(),
            resultSet.getString("mime_type"),
            resultSet.getInt("width"),
            resultSet.getInt("height"),
            resultSet.getBytes("content"),
            resultSet.getObject("uuid", UUID.class)
        );
    }
}
