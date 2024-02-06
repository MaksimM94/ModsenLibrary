FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} library.jar
ENTRYPOINT ["java","-jar","library.jar"]