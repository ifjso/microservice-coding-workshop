#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z eurekaserver $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Starting configuration service with Eureka endpoint: $EUREKASERVER_URI"
echo "********************************************************"

java -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI \
     -jar /usr/local/configserver/@project.build.finalName@.jar

