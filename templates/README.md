# Trivadis/RedHat Openshift Workshop - Part 11

## Openshift Templates

In this workshop you should write a template for configuring and deploying an *Elasticsearch* server.

1. Think on the objects to be created:
   - image stream
   - deployment
   - service
   - route
2. Adapt the given template file to your needs.
3. Install the template into your project (optionally install it globally) using *oc create -f elasticsearch-template.yml*
4. Check the paramters by calling *oc process --parameters elasticsearch-template*
5. Instantiate the template on CLI (*oc process -p ADMIN_USER=admin -p ADMIN_PWD=admin -p ... elasticsearch-template*)
6. Open the template on the Console UI. Open the console with *minishist console*


