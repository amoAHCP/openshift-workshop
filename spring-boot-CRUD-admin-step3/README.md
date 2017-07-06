# Trivadis/RedHat Openshift Workshop - Part 3

A simple Spring-boot 2 CRUD application with mongoDB. Application will be build and pushed to openshifts docker repository and than deployed manually. 

## Run the application

1. start minishift
2. oc login -u developer -p developer
3. docker login -u developer -p $(oc whoami -t) $(minishift openshift registry)
4. start a mongodb : oc create -f kube/mongoservice.yml && oc create -f kube/mongodeployment.yml
5. deploy config map: oc create -f kube/configmap.yml
6. create service: oc create -f kube/service.yml
7. mvn clean package 
8. eval $(minishift docker-env)
9. docker build -t step3 . 
10. docker tag step3 $(minishift openshift registry)/myproject/step3
11. docker push $(minishift openshift registry)/myproject/step3
12. update deployment.xml "image" with the pushed name (172.30.1.1:5000/myproject/step3)
13. create deployment: oc create -f kube/deployment.yml
14. get NodePort : oc export svc step3 | grep nodePort
15. verify service: curl http://$(minishift ip):$NODEPORT/index.html