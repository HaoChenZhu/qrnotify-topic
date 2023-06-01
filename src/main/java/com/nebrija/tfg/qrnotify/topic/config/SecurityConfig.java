package com.nebrija.tfg.qrnotify.topic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests((authorize) -> authorize
                        .antMatchers("/nebrija/qrnotify-topic/example", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/webjars/**", "/configuration/**")
                        .permitAll()
                        .anyRequest().authenticated()) // All requests require authentication
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // validates access tokens as JWTs
                .build();
    }

}
