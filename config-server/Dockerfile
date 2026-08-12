# --- etapa 1: Build (compilar el .jar) ---
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

RUN apk add --no-cache maven

# necesito todo el contexto del proyecto ya que es multimodulo
COPY . .

# -pl config-server -> me aseguro que solo compile el config server
# -am -> me aseguro que compile tambien las dependencias que necesita el config server
# -DskipTests -> salto la etapa de test
RUN mvn clean package -DskipTests -pl config-server -am

# --- etapa 2: Runtime (imagen final optimizada) ---
FROM eclipse-temurin:25-jre-alpine

# necesitamos curl para el health check y bash por si queremos entrar al contenedor
RUN apk add --no-cache curl bash

# directorio de trabajo
WORKDIR /app

# ---from=build -> copio lo de la etapa 1 (el .jar)
# app.jar -> es el nombre que le voy a dar a la imagen final
COPY --from=build /app/config-server/target/*.jar app.jar

EXPOSE 8888

ENTRYPOINT [ "java", "-jar", "app.jar" ]