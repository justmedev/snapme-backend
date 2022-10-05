package dev.justme.snapmebackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(a -> {
            a.antMatchers("/", "/error").permitAll().anyRequest().authenticated();
        }).exceptionHandling(e -> {
            e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        }).oauth2Login().redirectionEndpoint(e -> {
            e.baseUri("/");
        });
        return http.build();
    }
}