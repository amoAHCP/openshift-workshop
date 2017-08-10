FROM openjdk:8-jdk-alpine
RUN mkdir -p /usr/src/app
COPY . /usr/src/app
WORKDIR /usr/src/app

CMD ["java", "-jar","-Dspring.profiles.active=docker", "target/spring-boot-CRUD-admin-step2-0.0.1-SNAPSHOT.jar"]
