package by.bozhko.tg.bot.dao;

import by.bozhko.tg.bot.dao.model.Photo;

import java.util.List;

public interface PhotoDao {

    Photo getById(final Long id);

    void save(final Photo photo);

    void deleteById(final Long id);

    List<Photo> getAll();

    List<Long> getAllIds();
}
