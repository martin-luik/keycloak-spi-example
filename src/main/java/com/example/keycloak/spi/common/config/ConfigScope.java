package com.example.keycloak.spi.common.config;

public interface ConfigScope {
    String get(String key);
    String get(String key, String defaultValue);
}
