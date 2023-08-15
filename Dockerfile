FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=target/energy-api-v1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]