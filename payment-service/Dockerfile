FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} payment-service.jar
ENTRYPOINT ["java","-jar","payment-service.jar"]
EXPOSE 9191