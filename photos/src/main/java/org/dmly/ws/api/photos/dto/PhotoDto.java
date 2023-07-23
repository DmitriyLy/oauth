package org.dmly.ws.api.photos.dto;

public record PhotoDto(String userId,
                       String photoId,
                       String albumId,
                       String photoTitle,
                       String photoDescription,
                       String photoUrl) {
}
