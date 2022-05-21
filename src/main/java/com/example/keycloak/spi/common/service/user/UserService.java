package com.example.keycloak.spi.common.service.user;

import com.example.keycloak.spi.common.model.UserModel;
import lombok.val;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserProvider;

import java.util.Collections;
import java.util.Optional;

public class UserService {
    private static final String ID_CODE_COUNTRY_ATTRIBUTE = "id_code_country";
    private static final String DEFAULT_COUNTRY_CODE = "EST";

    public org.keycloak.models.UserModel getOrCreateUser(AuthenticationFlowContext context, UserModel userModel) {
        return getOrCreateUser(context.getSession(), userModel, context.getRealm().getId());
    }

    public org.keycloak.models.UserModel getOrCreateUser(KeycloakSession session, UserModel userModel, String realmId) {
        val userProvider = session.users();
        RealmModel realm = session.realms().getRealm(realmId);
        val existingUser = userProvider.getUserByUsername(
                realm,
                userModel.getPersonalNo()
        );

        return Optional.ofNullable(existingUser)
                .orElseGet(() -> createUser(realm, userProvider, userModel));
    }
    private org.keycloak.models.UserModel createUser(
            final RealmModel realm,
            final UserProvider userProvider,
            final UserModel userModel
    ) {
        val user = userProvider.addUser(realm, userModel.getPersonalNo());
        user.setEnabled(true);
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setAttribute(ID_CODE_COUNTRY_ATTRIBUTE, Collections.singletonList(DEFAULT_COUNTRY_CODE));
        return user;
    }
}
