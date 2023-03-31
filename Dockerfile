# Use a lightweight Java image as a base
FROM openjdk:8-jdk-alpine

# Set the working directory
WORKDIR /userService

# Copy the application JAR into the container
COPY target/userService-0.0.1-SNAPSHOT.jar .

# Set the command to start the microservice
CMD ["java", "-jar", "userService-0.0.1-SNAPSHOT.jar"]