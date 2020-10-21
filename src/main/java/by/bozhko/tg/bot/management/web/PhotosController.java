package by.bozhko.tg.bot.management.web;

import by.bozhko.tg.bot.dao.model.Photo;
import by.bozhko.tg.bot.dao.repository.PhotoRepository;
import by.bozhko.tg.bot.management.web.dto.PhotoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhotosController {

    private final PhotoRepository photoRepository;

    @CrossOrigin
    @GetMapping("/api/management/v1/photos")
    List<PhotoDto> getAll() {

        List<PhotoDto> allPhotos = new ArrayList<>();

        photoRepository.findAllByOrderByCreatedAtDesc().iterator().forEachRemaining(photo -> allPhotos.add(toPhotoDto(photo)));

        return allPhotos;
    }

    private PhotoDto toPhotoDto(Photo photo) {

        PhotoDto photoDto = new PhotoDto();
        photoDto.setId(photo.getId());
        photoDto.setUrl("/api/management/v1/photos/urls/" + photo.getUuid());
        photoDto.setCreatedAt(photo.getCreatedAt().toEpochMilli());

        return photoDto;
    }
}
