package by.bozhko.tg.bot.management.web;

import by.bozhko.tg.bot.dao.model.Photo;
import by.bozhko.tg.bot.dao.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PhotoUrlController {

    private final PhotoRepository photoRepository;

    @CrossOrigin
    @GetMapping(value = "/api/management/v1/photos/urls/{uuid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPhoto(@PathVariable("uuid") UUID uuid) {

        Optional<Photo> photo = photoRepository.getByUuid(uuid);

        return photo
            .map(value -> new ResponseEntity<>(value.getContent(), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
