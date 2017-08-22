# Trivadis/RedHat Openshift Workshop - Part 11

In this part we build our own S2I builder image for a static web server based on lighttpd.


## Prerequisites

1. Download and install the S2I CLI tool from https://github.com/openshift/source-to-image/releases/tag/v1.1.7 


## Building the S2I image

2. Create a new S2I builder skeleton: *s2i create lighttpd-centos7 s2i-lighttpd*
3. Have a look into the created folder. It should look somehow similar to this:

```
s2i-lighttpd/
  Dockerfile – This is a standard Dockerfile where we’ll define the builder image
  Makefile – a helper script for building and testing the builder image
  test/
    run – test script, testing if the builder image works correctly
    test-app/ – directory for your test application
  .s2i/bin/
    assemble – script responsible for building the application
    run – script responsible for running the application
    save-artifacts – script responsible for incremental builds, covered in a future article
    usage – script responsible for printing the usage of the builder image
```

4. Modify the *Dockerfile* as follows:
```
# We are basing our builder image on openshift base-centos7 image
FROM openshift/base-centos7

# Inform users who's the maintainer of this builder image
MAINTAINER Maciej Szulik <maszulik@redhat.com>

# Inform about software versions being used inside the builder
ENV LIGHTTPD_VERSION=1.4.35

# Set labels used in OpenShift to describe the builder images
LABEL io.k8s.description="Platform for serving static HTML files" \
      io.k8s.display-name="Lighttpd 1.4.35" \
      io.openshift.expose-services="8080:http" \
      io.openshift.tags="builder,html,lighttpd"

# Install the required software, namely Lighttpd and
RUN yum install -y lighttpd && \
    # clean yum cache files, as they are not needed and will only make the image bigger in the end
    yum clean all -y

# Defines the location of the S2I
# Although this is defined in openshift/base-centos7 image it's repeated here
# to make it clear why the following COPY operation is happening
LABEL io.openshift.s2i.scripts-url=image:///usr/local/s2i
# Copy the S2I scripts from ./.s2i/bin/ to /usr/local/s2i when making the builder image
COPY ./.s2i/bin/ /usr/local/s2i

# Copy the lighttpd configuration file
COPY ./etc/ /opt/app-root/etc

# Drop the root user and make the content of /opt/app-root owned by user 1001
RUN chown -R 1001:1001 /opt/app-root

# Set the default user for the image, the user itself was created in the base image
USER 1001

# Specify the ports the final image will expose
EXPOSE 8080

# Set the default CMD to print the usage of the image, if somebody does docker run
CMD ["usage"]
```

Once the Dockerfile is defined we can now start to fill in the remaining parts of the builder image.
Let’s deal with S2I scripts. 

5. We’ll start with assemble, which is responsible for building the application.
In our case it’ll just copy the source files into the directory from which they will be served by the 
Lighttpd server. Edit the *./bin/assemble script* to match the following:

```bash
#!/bin/bash -e
#
# S2I assemble script for the 'lighttpd-centos7' image.
# The 'assemble' script builds your application source ready to run.
#
# For more information refer to the documentation:
#  https://github.com/openshift/source-to-image/blob/master/docs/builder_image.md
#

echo "---> Installing application source"
cp -Rf /tmp/src/. ./
```

By default the s2i build places the application source in /tmp/src directory. This directory is where the source and other assets will be placed for the build process. You can modify this location by setting the io.openshift.s2i.destination label or passing --destination flag, in which case the sources will be placed in the src subdirectory of the directory you specified. The destination in the above command (./) is using working directory set in the openshift/base-centos7 image, which is set to be /opt/app-root/src.

6. Now it’s time to handle the second required script – *./bin/run*, which is responsible for
   running the application. In our case it’ll just handle starting the Lighttpd server:

```bash
#!/bin/bash -e
#
# S2I run script for the 'lighttpd-centos7' image.
# The run script executes the server that runs your application.
#
# For more information see the documentation:
#  https://github.com/openshift/source-to-image/blob/master/docs/builder_image.md
#

exec lighttpd -D -f /opt/app-root/etc/lighttpd.conf
```

We’re using exec for the above command to replace the run script’s process with the Lighttpd server process. This is done so that all the signals sent by docker will go to Lighttpd process and to have the output (stdout and stderr) of Lighttpd available when running this image.

7. Since we are not covering incremental builds in our example, we can safely **remove the *save-artifacts* script*.
8. Finally we put some information on how to use our builder image in the usage script:

```bash
#!/bin/bash -e

cat <<EOF
This is the lighttpd-centos7 S2I image:
To use it, install S2I: https://github.com/openshift/source-to-image

Sample invocation:

s2i build https://github.com/soltysh/sti-lighttpd.git --context-dir=test/test-app/ lighttpd-centos7 sample-app

You can then run the resulting image via:
docker run -p 8080:8080 sample-app
EOF
```

9. Check the script file permissions; they should all be *755*.
10. We also need a lighttpd configuration. So create *s2i-lighttpd/etc/lighttpd.conf* with the following
    contents:

```
# directory where the documents will be served from
server.document-root = "/opt/app-root/src"

# port the server listens on
server.port = 8080

# default file if none is provided in the URL
index-file.names = ( "index.html" )

# configure specific mimetypes, otherwise application/octet-stream will be used for every file
mimetype.assign = (
  ".html" => "text/html",
  ".txt" => "text/plain",
  ".jpg" => "image/jpeg",
  ".png" => "image/png"
)
```

11. Our builder image is ready. We can make sure it’s being built properly by invoking the *make* command
    in the s2i-lighttpd directory which, as you can see from the Makefile, invokes a plain docker build command.
12. Create *s2i-lighttpd/test/test-app/index.html* with the following contents:
```html
<!doctype html>
<html>
  <head>
    <title>test-app</title>
  </head>
<body>
  <h1>Hello from lighttpd served index.html!</h1>
</body>
```

13. With this file in place, we can now do our first S2I build: Let’s invoke the following command from the s2i-lighttpd directory:
```bash
    *s2i build test/test-app/ lighttpd-centos7 sample-app*
```
14. Now it’s time to actually test the resulting image. Run the image, publishing port 8080 from the container to one on localhost:
```bash
docker run -p 8080:8080 sample-app
```
15. Verify visiting http://localhost:8080/ and see the contents of the *index.html* file there.
16. Check the file permissions for *test/run*: it should be 700.
17. Run the S2I test suite with the following command from *thes2i-lighttpd* directory: *make test*

The tests are actually
- checking that the s2i build finished with no errors,
- checking that the usage script is working as expected,
- running the resulting image,
- checking that the running application container responds properly.

Congratulations, you’ve produced an S2I-enabled builder image that can, used with S2I (iow. Source Build strategy in OpenShift), consume html files in a git repository and produce a new image that will serve those html files when run. Generalizing this, it’s easy to see how you can define other builder images with appropriate assemble, run scripts that will consume other types of application source and produce images that build and run those applications.






