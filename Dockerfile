FROM ubuntu:latest AS build

COPY . .

RUN apt-get update
RUN apt-get install openjdk-11-jdk -y


RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:11-jdk-slim

EXPOSE 8080

COPY --from=build /target/manager-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]