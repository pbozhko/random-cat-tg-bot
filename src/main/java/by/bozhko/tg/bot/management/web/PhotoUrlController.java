package by.bozhko.tg.bot.management.web;

import by.bozhko.tg.bot.dao.PhotoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PhotoUrlController {

    private final PhotoDao photoDao;

    @GetMapping(value = "/api/management/v1/photos/{uuid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPhoto(@PathVariable("uuid") UUID uuid) {

        return new ResponseEntity<>(photoDao.getByUuid(uuid).getContent(), HttpStatus.OK);
    }
}
