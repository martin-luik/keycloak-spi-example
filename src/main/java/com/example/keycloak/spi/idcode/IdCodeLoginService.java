package com.example.keycloak.spi.idcode;

import com.example.keycloak.spi.common.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.keycloak.authentication.AuthenticationFlowContext;

@RequiredArgsConstructor
public class IdCodeLoginService implements LoginService {
    private final IdCodeAuthService idCodeAuthService;

    @Override
    public void login(AuthenticationFlowContext context) {
        idCodeAuthService.authenticate(context);
    }
}
