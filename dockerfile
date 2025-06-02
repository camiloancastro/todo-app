# Imagen base
FROM openjdk:17-jdk-slim

# Argumento opcional: nombre del JAR
ARG JAR_FILE=target/*.jar

# Copiar el JAR generado al contenedor
COPY ${JAR_FILE} app.jar

# Exponer puerto (ajusta si usas otro puerto)
EXPOSE 8081

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
