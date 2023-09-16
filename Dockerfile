# Use the OpenJDK 18 as the base image
FROM openjdk:18

# Copy the JAR file of the Spring Boot application into the image and rename it to app.jar
COPY target/websiteReview-0.0.1-SNAPSHOT.jar app.jar

# Set the entry point for the container to run the Java application with the app.jar file
ENTRYPOINT [ "java", "-jar", "app.jar" ]
