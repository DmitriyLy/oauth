package org.dmly.keycloak;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.CredentialModel;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.SubjectCredentialManager;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

import java.util.stream.Stream;

public class RemoteUserStorageProvider implements UserStorageProvider, UserLookupProvider, CredentialInputValidator {
    private final KeycloakSession keycloakSession;
    private final ComponentModel componentModel;
    private final UserApiService userApiService;

    public RemoteUserStorageProvider(KeycloakSession keycloakSession, ComponentModel componentModel, UserApiService userApiService) {
        this.keycloakSession = keycloakSession;
        this.componentModel = componentModel;
        this.userApiService = userApiService;
    }

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(RealmModel realmModel, String s) {
        return null;
    }

    @Override
    public UserModel getUserByUsername(RealmModel realmModel, String username) {
        UserModel userModel = null;

        User userDetails = userApiService.getUserDetails(username);
        if (userDetails != null) {
            userModel = createUserModel(realmModel, username);
        }
        return userModel;
    }

    private UserModel createUserModel(RealmModel realmModel, String username) {
        return new AbstractUserAdapter(keycloakSession, realmModel, componentModel) {
            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public SubjectCredentialManager credentialManager() {
                return null;
            }
        };
    }

    @Override
    public UserModel getUserByEmail(RealmModel realmModel, String s) {
        return null;
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String credentialType) {
        if (!supportsCredentialType(credentialType)) {
            return false;
        }

        return !getUserCredentialStore().getStoredCredentialByNameAndType(realmModel, userModel, credentialType, "").getConfig().isEmpty();
    }

    private UserCredentialStore getUserCredentialStore() {
        return new UserCredentialStore() {
            @Override
            public void updateCredential(RealmModel realm, UserModel user, CredentialModel cred) {

            }

            @Override
            public CredentialModel createCredential(RealmModel realm, UserModel user, CredentialModel cred) {
                return null;
            }

            @Override
            public boolean removeStoredCredential(RealmModel realm, UserModel user, String id) {
                return false;
            }

            @Override
            public CredentialModel getStoredCredentialById(RealmModel realm, UserModel user, String id) {
                return null;
            }

            @Override
            public Stream<CredentialModel> getStoredCredentialsStream(RealmModel realm, UserModel user) {
                return null;
            }

            @Override
            public Stream<CredentialModel> getStoredCredentialsByTypeStream(RealmModel realm, UserModel user, String type) {
                return null;
            }

            @Override
            public CredentialModel getStoredCredentialByNameAndType(RealmModel realm, UserModel user, String name, String type) {
                return null;
            }

            @Override
            public boolean moveCredentialTo(RealmModel realm, UserModel user, String id, String newPreviousCredentialId) {
                return false;
            }

            @Override
            public void close() {

            }
        };
    }

    @Override
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        PasswordVerificationResponse passwordVerificationResponse = userApiService.verifyPassword(userModel.getUsername(), credentialInput.getChallengeResponse());

        if (passwordVerificationResponse == null) {
            return false;
        }

        return passwordVerificationResponse.isResult();
    }
}
