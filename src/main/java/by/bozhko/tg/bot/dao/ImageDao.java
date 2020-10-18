package by.bozhko.tg.bot.dao;

import by.bozhko.tg.bot.dao.model.Image;

import java.util.List;

public interface ImageDao {

    Image getById(final Long id);

    void save(final Image image);

    void deleteById(final Long id);

    List<Image> getAll();
}
