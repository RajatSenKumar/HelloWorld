# Base image with OpenJDK 11
FROM openjdk:11-jdk-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven && apt-get clean

# Set the working directory
WORKDIR /app

# Copy the parent pom.xml and the source code
COPY pom.xml .
COPY hello_world_service ./hello_world_service
COPY hello_world_controller ./hello_world_controller

# Package the application
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:11-jdk-slim

# Set the working directory for the final image
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/hello_world_controller/target/hello_world_controller-0.0.1-SNAPSHOT.jar ./HelloWorld.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "HelloWorld.jar"]
