package com.example.keycloak.spi.idcode;

import com.example.keycloak.spi.common.config.CustomConfig;
import com.example.keycloak.spi.common.config.Environment;
import com.example.keycloak.spi.common.config.EnvironmentConfigConstant;
import com.example.keycloak.spi.common.model.UserModel;
import com.example.keycloak.spi.common.service.authentication.AuthenticationService;
import com.example.keycloak.spi.common.service.user.UserService;
import com.example.keycloak.spi.common.util.FormUtil;
import org.keycloak.authentication.AuthenticationFlowContext;

import javax.ws.rs.NotAllowedException;

import static com.example.keycloak.spi.common.util.FormUtil.PERSONAL_NO;

public class IdCodeAuthService extends AuthenticationService {
    public static final String DEFAULT_FIRST_NAME = "First";
    public static final String DEFAULT_LAST_NAME = "Last";
    private final CustomConfig customConfig;
    public IdCodeAuthService(CustomConfig config, UserService userService) {
        super(userService);
        customConfig = config;
    }

    @Override
    public void startAuthentication(AuthenticationFlowContext context) {
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        String environment = customConfig.get(EnvironmentConfigConstant.RUNTIME_ENVIRONMENT_CONFIG_KEY, Environment.PROD.toString());
        if(environment.equals(Environment.PROD.toString())) {
            throw new NotAllowedException("Not allowed to log in with ID Code!");
        }
        var personalNo = FormUtil.getFormParameter(context, PERSONAL_NO);

        var userModel = new UserModel();
        userModel.setPersonalNo(personalNo);
        userModel.setFirstName(DEFAULT_FIRST_NAME);
        userModel.setLastName(DEFAULT_LAST_NAME);
        var user = userService.getOrCreateUser(context, userModel);
        successAuthentication(context, user);
    }
}
