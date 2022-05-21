package com.example.keycloak.spi;

import com.example.keycloak.spi.common.util.FormUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

@RequiredArgsConstructor
@Slf4j
public class CustomLoginAuthenticator implements Authenticator {
    private final AuthenticationServiceFactory authenticationServiceFactory;

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        FormUtil.challenge(context);
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        try {
            authenticationServiceFactory.createAuthenticationService(context, null);
        } catch(Exception e) {
            log.error("Error during authentication", e);
            FormUtil.challenge(context);
        }
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return session.userCredentialManager().isConfiguredFor(realm, user,
                CustomLoginAuthenticatorFactory.PROVIDER_ID);
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {

    }

    @Override
    public void close() {

    }
}
