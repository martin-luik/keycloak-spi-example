package com.example.keycloak.spi;

import com.example.keycloak.spi.common.type.AuthenticationType;
import com.example.keycloak.spi.common.util.FormUtil;
import com.example.keycloak.spi.idcode.IdCodeLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;

import java.util.Optional;

import static com.example.keycloak.spi.common.util.FormUtil.AUTH_METHOD;

@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceFactory {
    private final IdCodeLoginService idCodeLoginService;
    public void createAuthenticationService(AuthenticationFlowContext context, AuthenticationType overrideAuthType) {
        String authMethod = Optional.ofNullable(FormUtil.getFormParameter(context, AUTH_METHOD))
                .orElse(String.valueOf(AuthenticationType.UNKNOWN));
        AuthenticationType authenticationMethod = AuthenticationType.fromString(authMethod);

        if(overrideAuthType != null) {
            authenticationMethod = overrideAuthType;
        }

        switch (authenticationMethod) {
            case ID_CODE:
                idCodeLoginService.login(context);
                break;
            default:
                context.failure(AuthenticationFlowError.IDENTITY_PROVIDER_ERROR);
                break;
        }
    }
}
