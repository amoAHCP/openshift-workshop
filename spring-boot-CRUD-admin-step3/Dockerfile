FROM openjdk:8-jdk-alpine
RUN mkdir -p /usr/src/app
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mkdir -p /usr/src/app/config

CMD ["java", "-jar", "target/spring-boot-CRDU-admin-step2-0.0.1-SNAPSHOT.jar","-cp","/usr/src/app/config"]
