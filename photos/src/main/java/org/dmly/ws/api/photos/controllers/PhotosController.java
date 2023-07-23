package org.dmly.ws.api.photos.controllers;

import org.dmly.ws.api.photos.dto.PhotoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotosController {
    @GetMapping
    public List<PhotoDto> getPhotos() {
        return List.of(
                new PhotoDto("1", "1", "albumIdHere", "Photo 1 title", "Photo 1 description", "Photo 1 URL"),
                new PhotoDto("1", "2", "albumIdHere", "Photo 2 title", "Photo 2 description", "Photo 2 URL")
        );
    }
}
