package org.dmly.ws.api.resource_server.controllers;

import org.dmly.ws.api.resource_server.dto.AlbumDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    @GetMapping
    public List<AlbumDto> getAlbums() {
        return List.of(
                new AlbumDto("1", "albumIdHere", "Album 1 title", "Album 1 description", "Album 1 URL"),
                new AlbumDto("2", "albumIdHere", "Album 2 title", "Album 2 description", "Album 2 URL")
        );
    }
}
