#base
FROM openjdk:17
ADD target/spring-cloud-config-server-0.0.1-SNAPSHOT.jar config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]