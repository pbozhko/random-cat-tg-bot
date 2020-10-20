package by.bozhko.tg.bot.service;

import by.bozhko.tg.bot.dao.model.Photo;
import by.bozhko.tg.bot.dao.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Slf4j
public class DefaultRandomImageService implements RandomImageService {

    private final PhotoRepository photoRepository;

    @Override
    public Optional<Photo> getImage() {

        List<Long> allIds = photoRepository.getAllIds();
        Random rand = new Random();
        Long randomId = allIds.get(rand.nextInt(allIds.size()));

        return photoRepository.findById(randomId);
    }
}
