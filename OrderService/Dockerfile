FROM eclipse-temurin:17
VOLUME /tmp
ARG JAR_FILE=build/libs/OrderService.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]