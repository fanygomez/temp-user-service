FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM build AS test
RUN echo "Running tests..." && mvn test

FROM openjdk:21-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar user-service.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "user-service.jar"]