#start with a base image containing java run time
FROM openjdk:17-jdk-slim
EXPOSE 8080
#the organization who maintains the image
MAINTAINER prateeksinghengnr@gmail.com
#Add the application's jar to the image
COPY target/MicroService-0.0.1-SNAPSHOT.jar MicroService-0.0.1-SNAPSHOT.jar
#execute the application
ENTRYPOINT ["java","-jar","MicroService-0.0.1-SNAPSHOT.jar"]