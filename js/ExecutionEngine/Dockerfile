FROM openjdk:8
ADD target/engine-0.0.1-SNAPSHOT.war engine-0.0.1-SNAPSHOT.war
EXPOSE 8085
ENTRYPOINT ["java","-jar","engine-0.0.1-SNAPSHOT.war"]