# Use OpenJDK 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file to the container
COPY target/*.jar app.jar

# Expose port (Render will override this with $PORT)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]