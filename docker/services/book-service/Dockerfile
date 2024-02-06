FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} book.jar
ENTRYPOINT ["java","-jar","book.jar"]