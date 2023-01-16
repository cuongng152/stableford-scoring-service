FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/stableford-scoring-service-0.0.1-SNAPSHOT.jar stableford-scoring-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "stableford-scoring-service-0.0.1-SNAPSHOT.jar"]