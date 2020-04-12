package com.todo.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "cache")
public class CacheConfig {
    private Map expiration;

    public Map<String, Integer> getExpiration() {
        return this.expiration;
    }

    public void setExpiration(Map<String, Integer> expiration) {
        this.expiration = expiration;
    }
}
