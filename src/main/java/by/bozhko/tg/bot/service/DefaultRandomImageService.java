package by.bozhko.tg.bot.service;

import by.bozhko.tg.bot.dao.ImageDao;
import by.bozhko.tg.bot.dao.model.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Slf4j
public class DefaultRandomImageService implements RandomImageService {

    private final ImageDao imageDao;

    @Override
    public Image getImage() {

        List<Long> allIds = imageDao.getAllIds();
        Random rand = new Random();
        Long randomId = allIds.get(rand.nextInt(allIds.size()));

        return imageDao.getById(randomId);
    }
}
