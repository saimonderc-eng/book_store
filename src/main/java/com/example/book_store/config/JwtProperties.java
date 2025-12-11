package com.example.book_store.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private String secret;

    private Long accessTokenExpiration = 1800000L;

    private Long refreshTokenExpiration = 3000000000L;
}
