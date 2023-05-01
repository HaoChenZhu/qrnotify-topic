package com.nebrija.tfg.qrnotify.topic.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import feign.Logger;

@Configuration
public class FeignClientConfiguration {

    @Value("${okta.oauth2.issuer}")
    private String issuer;

    @Value("${okta.oauth2.client-id}")
    private String clientId;

    @Value("${okta.oauth2.client-secret}")
    private String clientSecret;

    /*
        @Value("${api.client.token.grant-type:client_credentials}")
        private String apiClientTokenGrantType;
        */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor clientCredentialsTokenResolvingInterceptor() {
        return requestTemplate -> {
            OAuth2AccessToken accessToken = oAuth2RestTemplate().getAccessToken();
            requestTemplate.header("Authorization", "Bearer " + accessToken.getValue());
        };
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(issuer + "/v1/token");
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        return new OAuth2RestTemplate(resourceDetails);
    }
}

