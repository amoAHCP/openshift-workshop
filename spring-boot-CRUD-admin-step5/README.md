# Trivadis/RedHat Openshift Workshop - Part 5

A simple Spring-boot 2 CRUD application with mongoDB. In Step 5 the application will be deployed to openshift, by the help of the fabric8-maven-plugin using custom service/deployment and Dockerfile descriptors.
You can place your custom configuration in src/main/fabric8 & your Dockerfile in src/main/docker
## Run the application

1. oc login -u developer -p developer
2. minishift docker-env
3. create mongodb service: oc create -f kube/mongoservice.yml 
3.1 start a mongodb  oc create -f kube/mongodeployment.yml
4. mvn clean install fabric8:deploy
5. get service $ROUTE-URL: minishift openshift service list -n myproject
6. verify service: curl $ROUTE-URL/index.html
