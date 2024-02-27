package com.konoha;

import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jwt.JWTClaimsSet;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.lang.JoseException;
import org.keycloak.models.KeycloakSession;

import java.security.PrivateKey;
import java.text.ParseException;

public class JWEUtil {

    public static String decryptJWE(KeycloakSession session, String payloadSignedEncrypted, PrivateKey privateKey) throws JoseException, ParseException {
        String responsePayload = payloadSignedEncrypted;
        JsonWebEncryption jwEncryption = new JsonWebEncryption();
        jwEncryption.setKey(privateKey);
        jwEncryption.setCompactSerialization(responsePayload);
        String decryptedResponse = jwEncryption.getPlaintextString();

        return decryptedResponse;
    }
}
