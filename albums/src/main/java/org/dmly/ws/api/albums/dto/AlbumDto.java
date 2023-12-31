package org.dmly.ws.api.albums.dto;

public record AlbumDto(String userId,
                       String albumId,
                       String albumTitle,
                       String albumDescription,
                       String albumUrl) {
}
