FROM debian:bullseye-slim

RUN apt update \
    && apt install -y openjdk-17-jre

WORKDIR /app

# Copie o JAR para dentro do contêiner (use um caminho relativo)

COPY ./build/libs/com.architecture.eda-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
