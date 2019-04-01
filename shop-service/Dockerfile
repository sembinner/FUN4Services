FROM maven:3.6.0-jdk-8-alpine AS build
COPY . .
RUN mvn clean install
RUN mvn package

FROM openjdk:8

COPY --from=build target/shop-service-0.0.1-SNAPSHOT.jar ServicesApplication.jar

ENTRYPOINT ["java", "-jar", "ServicesApplication.jar"]