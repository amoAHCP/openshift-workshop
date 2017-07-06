# Trivadis/RedHat Openshift Workshop - Part 6

A simple Spring-boot 2 CRUD application with mongoDB. In Step 6 we separate the application into a frontend/gateway, a read controller and a write controller application. The frontend is contacting read/write by resolving the URL using environmental variables.
## Run the application

1. oc login -u developer -p developer
2. minishift docker-env
3. start a mongodb : oc create -f kube/mongoservice.yml && oc create -f kube/mongodeployment.yml
4. mvn clean install 
5. get service URL minishift openshift service list -n myproject --> find the frontend service url
6. verify service: curl $URL/index.html
