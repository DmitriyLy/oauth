package org.dmly.ws.clients.photoappwebclient.controllers;

import org.dmly.ws.api.albums.dto.AlbumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
public class AlbumsController {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Autowired
    private WebClient webClient;

    @GetMapping(path = "/albums")
    public String getAlbums(Model model,
                            @AuthenticationPrincipal OidcUser user) {

        String url = "http://localhost:8083/albums";

        List<AlbumDto> albums = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AlbumDto>>(){})
                .block();

        model.addAttribute("albums", albums);

        return "albums";
    }

}
