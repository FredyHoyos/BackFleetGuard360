# Fase de construcción (Build)
FROM maven:3.8.6-eclipse-temurin-17 AS builder

WORKDIR /app
COPY pom.xml .
# Copia solo los archivos necesarios para resolver dependencias primero (caché eficiente)
COPY src ./src

# Build del proyecto (generará el .jar en /app/target)
RUN mvn clean package -DskipTests

# Fase final (Runtime)
FROM eclipse-temurin:17-jre

WORKDIR /app
# Crea los directorios necesarios
RUN mkdir -p publica privada refresh_privada refresh_publica

# Copia el .jar desde la fase de construcción
COPY --from=builder /app/target/api-*.jar /app/api-v1.jar

ENTRYPOINT ["java", "-jar", "/app/api-v1.jar"]