package by.bozhko.tg.bot.service;

import by.bozhko.tg.bot.dao.model.Photo;

import java.util.Optional;

public interface RandomImageService {

    Optional<Photo> getImage();
}
