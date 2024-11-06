#FROM openjdk:23
#ARG JAR_FILE=target/*.jar
#COPY ./target/project3Group9-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
FROM openjdk:23
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]