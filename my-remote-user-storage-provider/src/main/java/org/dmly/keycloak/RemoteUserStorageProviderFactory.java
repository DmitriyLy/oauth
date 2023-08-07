package org.dmly.keycloak;

import jakarta.ws.rs.client.ClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class RemoteUserStorageProviderFactory implements UserStorageProviderFactory<RemoteUserStorageProvider> {
    public static final String PROVIDER_NAME = "my-remote-postgresql-user-storage-provider";

    @Override
    public RemoteUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        return new RemoteUserStorageProvider(keycloakSession, componentModel, buildHttpClient("http://localhost:8099"));
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    private UserApiService buildHttpClient(String uri) {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newBuilder().build();
        ResteasyWebTarget target = client.target(uri);
        return target
                .proxyBuilder(UserApiService.class)
                .classloader(UserApiService.class.getClassLoader())
                .build();
    }
}
