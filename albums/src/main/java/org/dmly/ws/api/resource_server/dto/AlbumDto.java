package org.dmly.ws.api.resource_server.dto;

public record AlbumDto(String userId,
                       String albumId,
                       String albumTitle,
                       String albumDescription,
                       String albumUrl) {
}
