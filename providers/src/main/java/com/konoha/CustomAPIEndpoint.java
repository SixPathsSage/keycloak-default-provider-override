package com.konoha;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.commons.codec.binary.Base64;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.crypto.KeyUse;
import org.keycloak.crypto.KeyWrapper;
import org.keycloak.models.KeycloakSession;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.security.PrivateKey;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomAPIEndpoint {

    private final KeycloakSession keycloakSession;

    public CustomAPIEndpoint(KeycloakSession keycloakSession) {
        this.keycloakSession = keycloakSession;
    }

    @GET
    @Path("/decrypt")
    @Produces("application/json")
    public Response decrypt() throws JoseException, ParseException {
        KeyWrapper keyWrapper = keycloakSession.keys().getKeysStream(keycloakSession.getContext().getRealm())
                .filter(Objects::nonNull)
                .filter(key -> key.getUse().equals(KeyUse.ENC))
                .filter(key -> key.getKid().equalsIgnoreCase(keycloakSession.getContext().getHttpRequest().getUri().getQueryParameters().getFirst("kid")))
                .findFirst().get();
        String decryptedJWT = JWEUtil.decryptJWE(keycloakSession, keycloakSession.getContext().getHttpRequest().getUri().getQueryParameters().getFirst("payload"), (PrivateKey) keyWrapper.getPrivateKey());

        return Response.ok(decryptedJWT).build();
    }
}
