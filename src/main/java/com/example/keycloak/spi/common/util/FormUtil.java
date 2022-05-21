package com.example.keycloak.spi.common.util;

import org.keycloak.authentication.AuthenticationFlowContext;

import javax.ws.rs.core.Response;

public final class FormUtil {
    public static final String PERSONAL_NO = "personalNo";
    public static final String AUTH_METHOD = "authMethod";

    private FormUtil() {}

    public static String getFormParameter(AuthenticationFlowContext context, String key) {
        if (context.getHttpRequest().getDecodedFormParameters().containsKey(key)) {
            return context.getHttpRequest().getDecodedFormParameters().getFirst(key);
        }
        return null;
    }

    public static void challenge(AuthenticationFlowContext context) {
        Response challenge = context.form().createForm("login.ftl");
        context.challenge(challenge);
    }
}
