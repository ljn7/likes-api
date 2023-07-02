FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY /likes/target/likes-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
