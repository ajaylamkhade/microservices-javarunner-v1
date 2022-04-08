FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} spring-cloud-config-server.jar
ENTRYPOINT ["java","-jar","spring-cloud-config-server.jar"]
EXPOSE 9196