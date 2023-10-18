FROM openjdk:11-jdk-slim AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY . .

RUN mvn clean install

FROM openjdk:11-jdk-slim

EXPOSE 8080

COPY --from=build /app/target/manager-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "app.jar"]