# Trivadis/RedHat Openshift Workshop - Part 6

A simple Spring-boot 2 CRUD application with mongoDB. In Step 6 we separate the application into a frontend/gateway, a read controller and a write controller application. The frontend is contacting read/write by resolving the URL using environmental variables.

## Run the application

1. Login to Openshift: *oc login -u developer -p developer*
2. Create mongodb service: *oc create -f kube/mongoservice.yml* 
3. Start a mongodb:  *oc create -f kube/mongodeployment.yml*
4. Build and deploy the project: *mvn clean install*
5. Get service $ROUTE-URL: *minishift openshift service list -n myproject* --> find the frontend service url
6. Verify service: *curl $ROUTE-URL/index.html* or open open it with a browser, e.g. http://frontend-step6-myproject.192.168.99.100.nip.io/index.html
7. Find valid ENV names: *oc get pods* --> execute: *oc exec $PODNAME env*, e.g *oc exec frontend-step6-4-8hpwc env*


