FROM maven:3.5-jdk-8-alpine AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jre-alpine
COPY --from=build /usr/src/app/target/*SNAPSHOT.jar /app/snapshot.jar

EXPOSE 8080
ENTRYPOINT java -jar /app/snapshot.jar

