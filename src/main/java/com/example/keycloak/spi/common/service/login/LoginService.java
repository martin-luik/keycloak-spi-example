package com.example.keycloak.spi.common.service.login;

import org.keycloak.authentication.AuthenticationFlowContext;

public interface LoginService {
    void login(AuthenticationFlowContext context);
}
