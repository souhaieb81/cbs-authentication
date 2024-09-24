FROM openjdk:17-jdk-alpine
RUN mkdir -p /app/config-repo
RUN mkdir -p /opt/application/cbs/secrets && chown -R root:root /opt/application/cbs/secrets
WORKDIR /app
ARG CBS_CONFIG_REPO
ARG JAR_FILE=target/cbs-authentication*.jar
COPY ${JAR_FILE} /app/app.jar

COPY override-config.sh /app/override-config.sh
RUN chmod +x /app/override-config.sh


EXPOSE 8080
ENV CONFIG_ENV=dev
ENV CONFIG_FILE=/app/config-repo/${CONFIG_ENV}/cbs-authentication.properties

ENTRYPOINT ["/bin/sh", "-c", "/app/override-config.sh && /app/override-config.sh && java -jar /app/app.jar"]

