# Trivadis/RedHat Openshift Workshop - Part 9

## Creating and using secrets

In this example we will create a simple *secret* and start a pod that prints out the secret information,in a very insecure way ;-)

### Already done

Secret values are *base64* encoded, so we would achieve this as follows:

- Encode a user id: *echo -n "admin" | base64* --> YWRtaW4=
- Encode a password: *echo -n "1f2d1e2e67df" | base64* --> MWYyZDFlMmU2N2Rm
- Include user/password in your secrets.yml:

---
apiVersion: v1
kind: Secret
metadata:
  name: test-secret
type: Opaque
data:
  username: YWRtaW4=
  password: MWYyZDFlMmU2N2Rm
---

## To run the example

1. Login to Openshift: *oc login -u dev -p dev*
2. Create the secret: *oc create -f secret.yml*
3. Run a pod reading the secret: *oc create -f secret-printer.yml*
4. Check the log output: *oc logs secret-printer*. You should see our password and uid.


