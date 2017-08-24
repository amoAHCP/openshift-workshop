# Trivadis/RedHat Openshift Workshop - Part 9

## Working with config maps in different environments

A simple nodejs application showing how to define and use a config map. Every project/environment will have their own values.

There will be 2 users of 2 projects for two different staging environments:

- User: *developer*  Project: *configmap-dev*
- User: *test* Project: *configmap-test*

## Run the examples

1. Login to Openshift: *oc login -u developer -p developer*
2. Create a new project: *oc new-project configmap-dev*
3. *oc create -f configmap-dev.json*
4. *oc create -f app-deployment.json*
5. *oc create -f app-build.json*

6. Switch user: *oc login -u test -p test*
7. Create a new project: *oc new-project configmap-test*
8. *oc create -f configmap-test.json*
9. *oc create -f app-deployment.json*

10.Login as admin: *oc login -u system:admin*
11.Set rules: *oc create -f roles.json*
12. Assign rules: *oc adm policy add-role-to-user image-tagger developer -n configmap-test*


13. Login in back as developer: *oc -u developer -p developer*
14. Build the app as developer: *oc start-build node-app -n configmap-dev --from-dir=.. --follow*


15. Get service $ROUTE-URL: *minishift openshift service list -n configmap-dev* --> find the frontend service url
16. verify service: *curl $ROUTE-URL*

## How to promote the application from dev to test

17. If you want to promote the application, use: *oc tag configmap-dev/node-app:latest configmap-test/node-app:latest*

You'll see how the application get deployed into the test environment:

18. Get service $ROUTE-URL: *minishift openshift service list -n configmap-test* --> find the frontend service url
19. verify service: curl $ROUTE-URL


