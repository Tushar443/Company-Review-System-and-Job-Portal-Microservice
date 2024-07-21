#base
FROM openjdk:17
ADD target/eureka-server-0.0.1-SNAPSHOT.jar servicereg.jar
ENTRYPOINT ["java","-jar","/servicereg.jar"]