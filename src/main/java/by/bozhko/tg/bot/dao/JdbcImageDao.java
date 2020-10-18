package by.bozhko.tg.bot.dao;

import by.bozhko.tg.bot.dao.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

@RequiredArgsConstructor
public class JdbcImageDao implements ImageDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Image> imageRowMapper;

    @Override
    public Image getById(final Long id) {

        return jdbcTemplate.queryForObject(
            "SELECT * FROM t_images WHERE id = ?",
            new Object[]{id},
            imageRowMapper
        );
    }

    @Override
    public void save(final Image image) {

        jdbcTemplate.update(
            "INSERT INTO t_images (title, created_at, content, width, height, mime_type) VALUES (?, ?, ?, ?, ?, ?)",
            new Object[]{
                image.getTitle(),
                Timestamp.from(image.getCreatedAt()),
                new SqlLobValue(image.getContent()),
                image.getWidth(),
                image.getHeight(),
                image.getMimeType(),
            },
            new int[]{Types.VARCHAR, Types.TIMESTAMP, Types.BLOB, Types.INTEGER, Types.INTEGER, Types.VARCHAR}
        );
    }

    @Override
    public void deleteById(final Long id) {

        jdbcTemplate.update("DELETE FROM t_images WHERE id = ?", id);
    }

    @Override
    public List<Image> getAll() {

        return jdbcTemplate.query(
            "SELECT * FROM t_images",
            imageRowMapper
        );
    }
}
