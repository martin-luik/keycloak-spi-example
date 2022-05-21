package com.example.keycloak.spi.common.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.Config;

@RequiredArgsConstructor
public class CustomConfig implements ConfigScope {
    protected final Config.Scope config;

    @Override
    public String get(String key) {
        return this.config.get(key);
    }

    @Override
    public String get(String key, String defaultValue){
        return this.config.get(key, defaultValue);
    }
}
