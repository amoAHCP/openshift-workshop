# Trivadis/RedHat Openshift Workshop - Part 3

A simple Spring-boot 2 CRUD application.

## Run the application

1. kubectl create -f kube/kube-registry.yaml 
2. kubectl port-forward --namespace kube-system $(kubectl get po -n kube-system | grep kube-registry-v0 | awk '{print $1;}') 5000:5000

1. start a mongodb : *docker run --name mongo -p 27017:27017 -d mongo mongod*
2. mvn clean package 
3. docker build -t step3 . 
4. docker run -p 8090:8090 --link mongo:mongo -it --name step2 step2