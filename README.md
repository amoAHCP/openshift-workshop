# Trivadis/RedHat Openshift Workshop

This workshop gives you a brief introduction of how to deploy a Java application step by step to openshift.  

- Step 1, is a simple Spring-boot CRUD application running locally, using a local mongodb instance

- In Step 2, we start the application as a Docker container, together with a mongodb container, locally

- In Step 3, we push the created Docker image to the openshift repository and deploy the mongodb and the application itself in openshift

- In Step 4, we automate the deployment to openshift by using the fabric8 maven plugin. Additionally we customize the service & deployment descriptors

- Step 5, demonstrates the usage of a custom Dockerfile together with the Fabric8 plugin
