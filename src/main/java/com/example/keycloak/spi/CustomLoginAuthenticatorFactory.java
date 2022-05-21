package com.example.keycloak.spi;

import com.example.keycloak.spi.common.config.CustomConfig;
import com.example.keycloak.spi.common.service.user.UserService;
import com.example.keycloak.spi.idcode.IdCodeAuthService;
import com.example.keycloak.spi.idcode.IdCodeLoginService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.LinkedList;
import java.util.List;

@Slf4j

public class CustomLoginAuthenticatorFactory implements AuthenticatorFactory {

    static final String PROVIDER_ID = "custom-login-form";
    static final String REFERENCE_CATEGORY = "theme/custom";
    static final String HELP_TEXT = "Custom Login Form.";
    static final String DISPLAY_TYPE = "Custom Login Form";

    private CustomConfig customConfig;

    private static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.REQUIRED,
            AuthenticationExecutionModel.Requirement.ALTERNATIVE,
            AuthenticationExecutionModel.Requirement.DISABLED
    };

    private final List<ProviderConfigProperty> configProperties = new LinkedList<>();

    @Override
    public String getDisplayType() {
        return DISPLAY_TYPE;
    }

    @Override
    public String getReferenceCategory() {
        return REFERENCE_CATEGORY;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return HELP_TEXT;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

    @Override
    public Authenticator create(KeycloakSession session) {
        val userService = new UserService();
        val idCodeAuthService = new IdCodeAuthService(customConfig, userService);

        val authenticationServiceFactory = new AuthenticationServiceFactory(
                new IdCodeLoginService(idCodeAuthService)
        );

        return new CustomLoginAuthenticator(authenticationServiceFactory);
    }

    @Override
    public void init(Config.Scope config) {
        this.customConfig = new CustomConfig(config);
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
