# Trivadis/RedHat Openshift Workshop - Part 4

A simple Spring-boot 2 CRUD application with mongoDB. In Step 4 the application will be deployed to openshift, by the help of the fabric8-maven-plugin using custom service/deployment descriptors
You can place your custom configuration in src/main/fabric8

## Run the application

1. oc login -u developer -p developer
2. create mongodb service: oc create -f kube/mongoservice.yml 
3 start a mongodb  oc create -f kube/mongodeployment.yml
4. mvn clean install fabric8:deploy 
5. get service $ROUTE-URL: minishift openshift service list -n myproject
6. verify service: curl $ROUTE-URL/index.html. It should look something like http://spring-boot-crud-admin-step4-myproject.192.168.99.100.nip.io/index.html
