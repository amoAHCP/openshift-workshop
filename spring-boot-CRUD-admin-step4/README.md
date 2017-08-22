# Trivadis/RedHat Openshift Workshop - Part 4

A simple Spring-boot 2 CRUD application with mongoDB. In Step 4 the application will be deployed to openshift, by the help of the fabric8-maven-plugin using custom service/deployment descriptors
You can place your custom configuration in *src/main/fabric8*

## Run the application

1. Login to Openshift: *oc login -u developer -p developer*
2. Create mongodb service: *oc create -f kube/mongoservice.yml*
3. Start a mongodb:  *oc create -f kube/mongodeployment.yml*
4. Build and deploy the project: *mvn clean install **fabric8:deploy** *
5. Get service $ROUTE-URL: *minishift openshift service list -n myproject*
6. Verify service: *curl $ROUTE-URL/index.html*
