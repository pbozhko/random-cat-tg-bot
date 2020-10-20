package by.bozhko.tg.bot.service;

import by.bozhko.tg.bot.dao.PhotoDao;
import by.bozhko.tg.bot.dao.model.Photo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Slf4j
public class DefaultRandomImageService implements RandomImageService {

    private final PhotoDao photoDao;

    @Override
    public Photo getImage() {

        List<Long> allIds = photoDao.getAllIds();
        Random rand = new Random();
        Long randomId = allIds.get(rand.nextInt(allIds.size()));

        return photoDao.getById(randomId);
    }
}
