package com.andrewsalygin.applicationservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig() {
}
