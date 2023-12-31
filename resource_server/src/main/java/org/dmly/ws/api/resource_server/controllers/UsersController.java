package org.dmly.ws.api.resource_server.controllers;

import org.dmly.ws.api.resource_server.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private Environment environment;

    @GetMapping("/status/check")
    public String status() {
        return "Working on port: " + environment.getProperty("local.server.port");
    }

    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    //@PreAuthorize("#id == #jwt.subject")
    //@Secured({"ROLE_developer"})
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Deleted user with id: " + id + " and JWT subject " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId() == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserDto getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return new UserDto("dmly", "dmly", id);
    }

}
