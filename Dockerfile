FROM openjdk:latest
VOLUME /tmp
ARG JAR_FILE=build/libs/OrderService1.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]