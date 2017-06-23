# Trivadis/RedHat Openshift Workshop - Part 2

A simple Spring-boot 2 CRUD application.

## Run the application

1. start a mongodb : *docker run --name mongo -p 27017:27017 -d mongo mongod*
2. mvn clean package 
3. docker build -t step2 . 
4. docker run -p 8090:8090 --link mongo:mongo -it --name step2 step2