# Trivadis/RedHat Openshift Workshop - Part 3

A simple Spring-boot 2 CRUD application.

## Run the application


1. start a mongodb : kubectl create -f kube/mongo-service.yaml && kubectl create -f kube/mongo-controller.yaml
2. deploy config map: kubectl create -f kube/configmap.yaml
3. mvn clean package 
4. docker build -t step3 . 
5. create service: kubectl create -f kube/service.yaml
6. create deployment: kubectl create -f kube/deployment.yaml
7. verify service: minikube service list (grep the URL of the step3 service and open in browser --> ${URL}/index.html)