package com.connector;

import org.keycloak.models.KeycloakSession;
import org.keycloak.protocol.oidc.OIDCWellKnownProviderFactory;
import org.keycloak.wellknown.WellKnownProvider;

public class CustomWellKnownEndpointProviderFactory extends OIDCWellKnownProviderFactory {

    @Override
    public WellKnownProvider create(KeycloakSession session) {
        return new CustomWellEndpointProvider(session);
    }
}
