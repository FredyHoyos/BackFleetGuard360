# Fase de construcción (Build)
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

# Build del proyecto (generará el .jar en /app/target)
RUN mvn clean package -DskipTests

# Fase final (Runtime)
FROM eclipse-temurin:17-jre

WORKDIR /app
# Crea los directorios necesarios
RUN mkdir -p publica privada refresh_privada refresh_publica

# Copia el .jar desde la fase de construcción
COPY --from=build /app/target/api-*.jar /app/api-v1.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app/api-v1.jar"]