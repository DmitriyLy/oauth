package org.dmly.ws.api.resource_server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurity {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        httpSecurity
                //.cors(withDefaults())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.GET, "/users/status/check")
                                //.hasAuthority("SCOPE_profile")
                                .hasRole("developer")
                                //.hasAuthority("ROLE_developer")
                                //.hasAnyRole()
                                .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 ->
                    oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                )

        ;

        return httpSecurity.build();
    }

    /*@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

}
