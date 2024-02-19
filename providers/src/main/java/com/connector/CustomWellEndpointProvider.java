package com.connector;

import org.keycloak.models.CibaConfig;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.protocol.oidc.OIDCLoginProtocolService;
import org.keycloak.protocol.oidc.OIDCWellKnownProvider;
import org.keycloak.protocol.oidc.endpoints.AuthorizationEndpoint;
import org.keycloak.protocol.oidc.endpoints.TokenEndpoint;
import org.keycloak.protocol.oidc.grants.ciba.CibaGrantType;
import org.keycloak.protocol.oidc.grants.device.endpoints.DeviceEndpoint;
import org.keycloak.protocol.oidc.par.endpoints.ParEndpoint;
import org.keycloak.protocol.oidc.representations.MTLSEndpointAliases;
import org.keycloak.protocol.oidc.representations.OIDCConfigurationRepresentation;
import org.keycloak.services.Urls;
import org.keycloak.services.clientregistration.ClientRegistrationService;
import org.keycloak.services.resources.RealmsResource;
import org.keycloak.urls.UrlType;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CustomWellEndpointProvider extends OIDCWellKnownProvider {

    private final KeycloakSession session;

    public CustomWellEndpointProvider(KeycloakSession session) {
        this(session, null, true);
    }

    public CustomWellEndpointProvider(KeycloakSession session, Map<String, Object> openidConfigOverride, boolean includeClientScopes) {
        super(session, openidConfigOverride, includeClientScopes);
        this.session = session;
    }

    @Override
    public Object getConfig() {
        OIDCConfigurationRepresentation config = new OIDCConfigurationRepresentation();
        return config;
    }

    private List<String> getSupportedBackchannelAuthenticationRequestSigningAlgorithms() {
        return Arrays.asList("RS", "PS");
    }
}
