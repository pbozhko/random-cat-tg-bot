package by.bozhko.tg.bot.dao.repository;

import by.bozhko.tg.bot.dao.model.Photo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {

    Photo getByUuid(UUID uuid);

    @Query("SELECT id FROM Photo")
    List<Long> getAllIds();

    List<Photo> findAllByOrderByCreatedAtDesc();
}
