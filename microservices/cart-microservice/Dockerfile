# --- etapa 1: Build (compilar el .jar) ---
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

RUN apk add --no-cache maven

COPY . .

RUN mvn clean package -DskipTests -pl microservices/cart-microservice -am

# --- etapa 2: Runtime (imagen final optimizada) ---
FROM eclipse-temurin:25-jre-alpine

RUN apk add --no-cache curl bash

WORKDIR /app

COPY --from=build /app/microservices/cart-microservice/target/*.jar app.jar

EXPOSE 8098

ENTRYPOINT [ "java", "-jar", "app.jar" ]