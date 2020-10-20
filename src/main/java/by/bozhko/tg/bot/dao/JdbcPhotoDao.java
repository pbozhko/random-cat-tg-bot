package by.bozhko.tg.bot.dao;

import by.bozhko.tg.bot.dao.model.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class JdbcPhotoDao implements PhotoDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Photo> photoRowMapper;

    @Override
    public Photo getById(final Long id) {

        return jdbcTemplate.queryForObject(
            "SELECT * FROM t_photos WHERE id = ?",
            new Object[]{id},
            photoRowMapper
        );
    }

    @Override
    public Photo getByUuid(final UUID uuid) {

        return jdbcTemplate.queryForObject(
            "SELECT * FROM t_photos WHERE uuid = ?",
            new Object[]{uuid},
            photoRowMapper
        );
    }

    @Override
    public void save(final Photo photo) {

        jdbcTemplate.update(
            "INSERT INTO t_photos (title, created_at, content, width, height, mime_type, uuid) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",
            new Object[]{
                photo.getTitle(),
                Timestamp.from(photo.getCreatedAt()),
                new SqlLobValue(photo.getContent()),
                photo.getWidth(),
                photo.getHeight(),
                photo.getMimeType(),
                photo.getUuid()
            },
            new int[]{
                Types.VARCHAR, Types.TIMESTAMP, Types.BLOB, Types.INTEGER,
                Types.INTEGER, Types.VARCHAR, Types.JAVA_OBJECT
            }
        );
    }

    @Override
    public void deleteById(final Long id) {

        jdbcTemplate.update("DELETE FROM t_photos WHERE id = ?", id);
    }

    @Override
    public List<Photo> getAll() {

        return jdbcTemplate.query(
            "SELECT * FROM t_photos",
            photoRowMapper
        );
    }

    @Override
    public List<Long> getAllIds() {

        return jdbcTemplate.query(
            "SELECT id FROM t_photos",
            (resultSet, i) -> resultSet.getLong("id")
        );
    }
}
