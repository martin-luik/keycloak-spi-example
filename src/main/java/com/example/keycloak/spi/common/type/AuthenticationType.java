package com.example.keycloak.spi.common.type;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum AuthenticationType {
    ID_CODE,
    UNKNOWN;

    public static AuthenticationType fromString(String type) {
        try {
            return AuthenticationType.valueOf(type);
        } catch (Exception exception) {
            log.error("Unable to map '{}' to AuthenticationType.", type, exception);
            return UNKNOWN;
        }
    }
}
