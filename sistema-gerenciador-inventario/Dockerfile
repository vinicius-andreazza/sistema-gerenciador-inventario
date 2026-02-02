FROM maven:3.9.11-sapmachine-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM mosipid/openjdk-17-jre

COPY --from=build /app/target/sistema-gerenciador-inventario-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]