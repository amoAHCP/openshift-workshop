# Trivadis/RedHat Openshift Workshop

This workshop gives you a brief introduction of how to deploy a Java application step by step to openshift.  

Pre-requirements:
- install Docker (if you run on Windows be aware if you are using docker on Hyper-v or with docker-machine/Virtualbox. Will weii provide some hints for docker-machine useres in the readmes) 
- powershell if you are running on windows
- install openshift client tools--> https://developers.openshift.com/managing-your-applications/client-tools.html
- install minishift --> https://docs.openshift.org/latest/minishift/getting-started/installing.html
- check versions: 
    *minishift version* --> v1.4.1
    *minishift openshift version*
      openshift v3.6.0+
      kubernetes v1.6.1
      etcd 3.2.1
      
      
    


- Step 1, is a simple Spring-boot CRUD application running locally, using a local mongodb instance

- In Step 2, we start the application as a Docker container, together with a mongodb container, locally

- In Step 3, we push the created Docker image to the openshift repository and deploy the mongodb and the application itself in openshift

- In Step 4, we automate the deployment to openshift by using the fabric8 maven plugin. Additionally we customize the service & deployment descriptors

- Step 5, demonstrates the usage of a custom Dockerfile together with the Fabric8 plugin
