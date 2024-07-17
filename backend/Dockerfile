FROM maven:3.6.3-openjdk-17-slim AS build
COPY src /backend/src
COPY pom.xml /backend
RUN mvn -f /backend/pom.xml -DskipTests package

FROM openjdk:20-ea-13-jdk-oraclelinux8
COPY --from=build "backend/target/unla-test-career-0.0.1-SNAPSHOT.jar" "app.jar"
ENTRYPOINT ["java", "-jar", "/app.jar"]