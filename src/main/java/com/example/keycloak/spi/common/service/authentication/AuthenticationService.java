package com.example.keycloak.spi.common.service.authentication;

import com.example.keycloak.spi.common.service.user.UserService;
import com.example.keycloak.spi.common.util.FormUtil;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.models.UserModel;

import static com.example.keycloak.spi.common.constants.ErrorConstants.AUTH_EXCEPTION_REASON;

public abstract class AuthenticationService {
    protected UserService userService;

    protected AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public abstract void startAuthentication(AuthenticationFlowContext context);

    public abstract void authenticate(AuthenticationFlowContext context);

    protected void successAuthentication(final AuthenticationFlowContext context, final UserModel user) {
        context.setUser(user);
        context.success();
        context.getEvent().user(user);
    }

    protected void setAuthenticationError(final AuthenticationFlowContext context, final String exceptionReason) {
        context.form().setAttribute(AUTH_EXCEPTION_REASON, exceptionReason);
        context.getAuthenticationSession().clearAuthNotes();
        FormUtil.challenge(context);
    }
}
