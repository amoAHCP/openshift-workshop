# Trivadis/RedHat Openshift Workshop - Part 3

A simple Spring-boot 2 CRUD application with mongoDB. Application will be build and pushed to openshifts docker repository and than deployed manually. 

## Run the application

1. Start minishift (for docker-machine users: *minishift start --vm-driver=virtualbox*)
2. Login to Openshift: *oc login -u developer -p developer*
3. Set the local docker environment: *eval $(minishift docker-env)* --> **on Windows** *minishift docker-env*
4. Login to the docker registry: *docker login -u developer -p $(oc whoami -t) $(minishift openshift registry)* **on Windows:** evaluate the expression *oc whoami -t* & *minishift openshift registry* and replace them in the command  
5. Create mongodb service: *oc create -f kube/mongoservice.yml*
6. Start a mongodb  *oc create -f kube/mongodeployment.yml*
7. Deploy config map: *oc create -f kube/configmap.yml*
8. Create service: *oc create -f kube/service.yml*
9. Build the project: *mvn clean package* 
10. Build the docker image: *docker build -t step3 . *
11. Tag the built docker image: docker tag step3 $(minishift openshift registry)/myproject/step3 **on Windows:** first execute *minishift openshift registry* and put the $OUTPUT to *docker tag step3 $OUTPUT/myproject/step3*
12. Push the built image to the openshift registry: *docker push $(minishift openshift registry)/myproject/step3*
13. Ensure the deployment.xml "image" entry matches the pushed name (*172.30.1.1:5000/myproject/step3* , check the prefix by executing **minishift openshift registry**) 
14. Create deployment: *oc create -f kube/deployment.yml*
15. get $NODEPORT : *oc export svc step3 | grep nodePort*
16. Verify service: *curl http://$(minishift ip):$NODEPORT/index.html* or point your browser to http://192.168.99.100:31698/index.html (example)
