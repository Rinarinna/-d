FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/yourproject.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
