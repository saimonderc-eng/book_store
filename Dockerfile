FROM  eclipse-temurin:21-jre
WORKDIR /app
COPY build/libs/*.jar app.jar
COPY src/main/resources/application.yml /app/application.ymldo
ENTRYPOINT ["java", "-jar", "app.jar"]