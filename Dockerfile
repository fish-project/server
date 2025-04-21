# Stage 1
FROM maven:3.8.7-eclipse-temurin-17-alpine as tar_build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=tar_build /app/target/app.jar app.jar

# Run
CMD ["java", "-jar", "app.jar"]