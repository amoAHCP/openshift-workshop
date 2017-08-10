# Trivadis/RedHat Openshift Workshop - Part 7

A simple Spring-boot 2 CRUD application with mongoDB. In Step 7 we add a second frontend (using Wildfly-swarm). The Spring frontend now uses the service names of read and write to discover them and the wildfly frontend is using a Fabric8 extension to discover the services.
## Run the application

1. oc login -u developer -p developer
2. minishift docker-env
3. start a mongodb : oc create -f kube/mongoservice.yml && oc create -f kube/mongodeployment.yml
4. grant rights: oadm policy add-role-to-user view system:serviceaccount:myproject:default -n myproject
5. mvn clean install 
6. get service URL minishift openshift service list -n myproject --> find both frontend service urls
7. verify frontend: curl $URL/index.html
8. verify wildfly-frontend: curl $URL/index.html (supports only read operations)
