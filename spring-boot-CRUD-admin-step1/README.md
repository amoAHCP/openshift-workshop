# Trivadis/RedHat Openshift Workshop - Part 1

A simple Spring-boot 2 CRUD application.

## Run the application

1. start a mongodb at *@localhost* **or** *docker run --name mongo -p 27017:27017 -d mongo mongod*
2. run *mvn clean package spring-boot:run* 
3. access at http://localhost:8090/index.html