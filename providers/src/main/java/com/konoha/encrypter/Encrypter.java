package com.konoha.encrypter;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.lang.JoseException;

public class Encrypter {
    public static void main(String[] args) throws JoseException {
        String jwkStr = "{\n" +
                "\"kid\": \"CMtChlU0dyWNwWjyAGpCkkjToaxmxEFViRJhY_yNVJA\",\n" +
                "\"kty\": \"RSA\",\n" +
                "\"alg\": \"RSA-OAEP-256\",\n" +
                "\"use\": \"enc\",\n" +
                "\"n\": \"y2_B5csNKdbt32gJYR3g-E9SdO3yt7BBFSupHQs6kZNJuyimJqwpjoqa_45R8p8bKrCfamAPfoEDtwv9DkjW2xAHpQaUFIodq5qi9H2wFDKQX56WtKmax84xY1QHGDWR8OnGI46oQR0m0Y6ICZRd9x3TrR0iJ7-2bGn75GZDNVsSl_10EPW7uRE-NCbBnCsdwHrYD-YAMGta984m4gIB_aSmz0O1DlU6rHnfLETHOIE3mzUDNvi0RyMJacOIrqoToJCfq_u-j-OGB8fLQ4790yrpJc4JV3cssLTIQjhh9mAXD4tC0Odg-3Tkc3smBcOeA-C0zUF7Y7G7qgVtfZot9w\",\n" +
                "\"e\": \"AQAB\",\n" +
                "\"x5c\": [\n" +
                "\"MIICmzCCAYMCBgGN67ogfjANBgkqhkiG9w0BAQsFADARMQ8wDQYDVQQDDAZtYXN0ZXIwHhcNMjQwMjI3MTc1OTIwWhcNMzQwMjI3MTgwMTAwWjARMQ8wDQYDVQQDDAZtYXN0ZXIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDLb8Hlyw0p1u3faAlhHeD4T1J07fK3sEEVK6kdCzqRk0m7KKYmrCmOipr/jlHynxsqsJ9qYA9+gQO3C/0OSNbbEAelBpQUih2rmqL0fbAUMpBfnpa0qZrHzjFjVAcYNZHw6cYjjqhBHSbRjogJlF33HdOtHSInv7ZsafvkZkM1WxKX/XQQ9bu5ET40JsGcKx3AetgP5gAwa1r3zibiAgH9pKbPQ7UOVTqsed8sRMc4gTebNQM2+LRHIwlpw4iuqhOgkJ+r+76P44YHx8tDjv3TKuklzglXdyywtMhCOGH2YBcPi0LQ52D7dORzeyYFw54D4LTNQXtjsbuqBW19mi33AgMBAAEwDQYJKoZIhvcNAQELBQADggEBACDnS7C/nKGYMjmNJu9QfFued550xZ/2zIgqH9m+eFlt5ueo0h/CQImt+Cu60/AzqL38XVbfv3T5ZSQre/mwpjyY5BV6QypjCAafKPDIlDIryHCc83hOkbU4AgL8fa5mtoDDJ4fd1wLBlooGZ9n/wTJ5on5ihZSCOx1ZYlSQfNB0G34Rr+/9xj1nh4ekLkTtZKpY9Ie6HPRFzJadN7NH8T8igDSq5Z7FbKZpodhiBgIldchxxNC+DvPUc7x7X5syDshJOC2BZMQ0Zkf6xV+vtDmdfsAap3uidZlwtsjq+OsfYlZvTD1B2c6bEHhrYz7JH+xkmBvB5QWwQ9atd3JJg9M=\"\n" +
                "],\n" +
                "\"x5t\": \"L_762LmM35iWklOVTz_m4TTIIeo\",\n" +
                "\"x5t#S256\": \"WluYXE_VNRdZte3qbV9gcLUnwkOx60B6wTAIR9REwSE\"\n" +
                "}";

        JsonWebKey jwk = JsonWebKey.Factory.newJwk(jwkStr);
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        jwe.setKey(jwk.getKey());
        String payload = "{\"name\":\"admin\"}";
        jwe.setPayload(payload);
        String jwt = jwe.getCompactSerialization();

        System.out.println("Encrypted JWT: " + jwt);
    }
}
