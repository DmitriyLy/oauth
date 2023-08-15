package org.dmly.photoapp.orders.controllers;

import lombok.RequiredArgsConstructor;
import org.dmly.photoapp.orders.dto.OrderDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final RestTemplate restTemplate;

    @GetMapping(path = "/orders")
    public String getOrders(Model model, @RegisteredOAuth2AuthorizedClient(registrationId = "users-client-oidc") OAuth2AuthorizedClient authorizedClient) {

        String tokenValue = authorizedClient.getAccessToken().getTokenValue();

        System.out.println("tokenValue = " + tokenValue);

        String url = "http://localhost:8091/orders";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(tokenValue);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<OrderDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<OrderDto>>(){});

        List<OrderDto> ordersList = responseEntity.getBody();

        model.addAttribute("orders", ordersList);
        return "orders-page";
    }

}
