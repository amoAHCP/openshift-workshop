# Trivadis/RedHat Openshift Workshop - Part 8

A simple nodejs application showing how to define and use a config map.


## Run the application

1. Login to Openshift: *oc login -u developer -p developer*
2. Create a new project: *oc new-project configmap-1*
3. Create a config map: *oc create -f configmap.json*
4. Create the application deployment: *oc create -f app-deployment.json*
5. Create the application image: *oc create -f app-build.json*
6. Get service $ROUTE-URL: *minishift openshift service list -n configmap-1 --> find the frontend service url
7. verify service: curl $ROUTE-URL

