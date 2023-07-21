package org.dmly.ws.api.resource_server.dto;

public record PhotoDto(String userId,
                       String photoId,
                       String albumId,
                       String photoTitle,
                       String photoDescription,
                       String photoUrl) {
}
