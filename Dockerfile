FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR app

ADD ./target/messaging-1.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "messaging-1.0-SNAPSHOT.jar"]