# Trivadis/RedHat Openshift Workshop - Part 5

A simple Spring-boot 2 CRUD application with mongoDB. In Step 5 the application will be deployed to openshift, by the help of the fabric8-maven-plugin using custom service/deployment and Dockerfile descriptors.
You can place your custom configuration in *src/main/fabric8* & your Dockerfile in *src/main/docker*

## Run the application

1. Login to Openshift: *oc login -u developer -p developer*
2. Setup your Docker environment: *eval $(minishift docker-env)*
3. Create mongodb service: *oc create -f kube/mongoservice.yml*
4. Start a mongodb: *oc create -f kube/mongodeployment.yml*
5. Build and deploy your project: *mvn clean install* **fabric8:deploy**
6. Get service $ROUTE-URL: *minishift openshift service list -n myproject*
7. Verify the service: *curl $ROUTE-URL/index.html* or open it in your browser (example: http://spring-boot-crud-admin-step5-myproject.192.168.99.100.nip.io ).
