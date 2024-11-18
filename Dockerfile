FROM ubuntu:latest AS base

COPY . .

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y
RUN mvn clean install -X

FROM openjdk:17-slim
EXPOSE 8080

COPY --from=base target/N.I.B-0.0.1-SNAPSHOT.jar N.I.B.jar

ENTRYPOINT ["java", "-jar", "N.I.B.jar"]
