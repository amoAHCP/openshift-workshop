# Trivadis/RedHat Openshift Workshop - Part 2

A simple Spring-boot 2 CRUD application.

## Run the application

1. Start a mongodb : **docker run --name mongo -p 27017:27017 -d mongo mongod**
2. Check with *docker ps* your Mongo DB instance is running
2. Run: *mvn clean package* 
3. Build the image: *docker build -t step2 .* 
4. Start the container and link to mongodb: *docker run -p 8090:8090 --link mongo:mongo -it --name step2 step2*
5. Access at http://localhost:8090/index.html (looks the same as in the previous step), if you are using docker-machine, find out the ip address by typing *docker-machine ip* 

