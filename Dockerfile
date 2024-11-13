FROM debian:bullseye-slim

RUN apt update \
    && apt install -y openjdk-17-jre

WORKDIR /app

COPY ./build/libs/com.architecture.eda-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
