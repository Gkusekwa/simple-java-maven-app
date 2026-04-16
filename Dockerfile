FROM eclipse-temurin:21-jre
COPY target/my-app-1.0-SNAPSHOT.jar /app/my-app.jar
ENTRYPOINT ["java", "-jar", "/app/my-app.jar"]
