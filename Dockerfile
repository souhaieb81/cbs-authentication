FROM eclipse-temurin:21-jdk

WORKDIR /app

ARG JAR_FILE=target/cbs-authentication*.jar

COPY ${JAR_FILE} /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

