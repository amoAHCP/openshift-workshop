# Trivadis/RedHat Openshift Workshop - Part 2

A simple Spring-boot 2 CRUD application.

## Run the application

1. start a mongodb : **docker run --name mongo -p 27017:27017 -d mongo mongod**
2. check with *docker ps* your Mongo DB instance is running
2. run: *mvn clean package* 
3. build the image: *docker build -t step2 .* 
4. start the container and link to mongodb: *docker run -p 8090:8090 --link mongo:mongo -it --name step2 step2*
5. access at http://localhost:8090/index.html (looks the same as in the previous step)

