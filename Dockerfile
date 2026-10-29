FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/flight-booking-2.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
