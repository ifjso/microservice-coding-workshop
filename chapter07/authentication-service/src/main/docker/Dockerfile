FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd && apk add curl
RUN mkdir -p /usr/local/authenticationservice
ADD @project.build.finalName@.jar /usr/local/authenticationservice
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh

