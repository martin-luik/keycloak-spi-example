FROM adoptopenjdk/openjdk11 as builder

ADD . /
RUN chmod +x gradlew
RUN ./gradlew shadowJar

FROM jboss/keycloak:16.1.1

COPY --from=builder build/libs/keycloak-spi-all.jar /opt/jboss/keycloak/standalone/deployments/keycloak-spi-all.jar
COPY --from=builder src/main/resources/theme/example-web  /opt/jboss/keycloak/themes/example-web/
COPY --from=builder cli/authenticator-configuration.cli /opt/jboss/tools/cli/authenticator-configuration.cli
COPY --from=builder config/example-realm.json /opt/jboss/keycloak/example-realm.json

RUN $JBOSS_HOME/bin/jboss-cli.sh --file=/opt/jboss/tools/cli/authenticator-configuration.cli
RUN rm -rf /opt/jboss/keycloak/standalone/configuration/standalone_xml_history/current/*

EXPOSE 8080
ENTRYPOINT [ "/opt/jboss/tools/docker-entrypoint.sh" ]
