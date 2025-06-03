FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

RUN mkdir -p /app/publica /app/privada /app/refresh_privada /app/refresh_publica

COPY target/api-0.0.1-SNAPSHOT.jar /app/api-v1.jar

ENTRYPOINT ["java", "-jar", "/app/api-v1.jar"]