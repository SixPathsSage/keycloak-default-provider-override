FROM quay.io/keycloak/keycloak:21.1.0

ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true
ENV DEBUG=true
ENV DEBUG_PORT="*:8000"

COPY target/connector-event-listener.jar /opt/keycloak/providers

RUN /opt/keycloak/bin/kc.sh build
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]