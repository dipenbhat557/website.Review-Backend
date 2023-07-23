FROM openjdk:17

COPY target/websiteReview-0.0.1-SNAPSHOT.jar app.jar

#ADD target/websiteReview-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]