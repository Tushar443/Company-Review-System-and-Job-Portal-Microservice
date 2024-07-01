#base
FROM openjdk:17
ADD target/job-microservice-0.0.1-SNAPSHOT.jar job-microservice.jar
#EXPOSE 8081
ENTRYPOINT ["java","-jar","job-microservice.jar"]