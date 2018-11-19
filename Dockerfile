FROM openjdk:8-jdk-alpine
COPY ./target/superhero-sg-0.0.1-SNAPSHOT.jar /src/main/java/
WORKDIR /src/main/java/
EXPOSE 8080
CMD ["java", "-jar", "superhero-sg-0.0.1-SNAPSHOT.jar"]