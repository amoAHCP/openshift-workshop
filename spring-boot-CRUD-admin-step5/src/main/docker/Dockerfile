FROM openjdk:8-jdk-alpine
RUN mkdir -p /usr/src/app
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mkdir -p /usr/src/app/config
# all dependencies and the builded jar are copied to a folder called "maven" by the fabric8 plugin
CMD ["java", "-jar", "maven/spring-boot-CRUD-admin-step5-0.0.1-SNAPSHOT.jar","-cp","/usr/src/app/config"]
