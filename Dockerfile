FROM maven:3.8.7-openjdk-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:21-jdk
WORKDIR /app

COPY --from=build target/*.jar user-service.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "user-service.jar"]