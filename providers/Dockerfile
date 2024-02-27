FROM quay.io/keycloak/keycloak:21.0.2

ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true

COPY target/sample-event-listener.jar /opt/keycloak/providers

RUN /opt/keycloak/bin/kc.sh build
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]