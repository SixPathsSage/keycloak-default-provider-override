package com.konoha;

import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

public class CustomAPIProvider implements RealmResourceProvider {

    KeycloakSession keycloakSession;
    public CustomAPIProvider(KeycloakSession keycloakSession) {
        this.keycloakSession = keycloakSession;
    }

    @Override
    public Object getResource() {
        return new CustomAPIEndpoint(keycloakSession);
    }

    @Override
    public void close() {

    }
}
