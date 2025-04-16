# ---------- Step 1: Build Stage ----------
FROM maven:3.8.8-eclipse-temurin-21 AS build

# Set working directory inside container
WORKDIR /app

# Copy project files
COPY . .

# Build the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# ---------- Step 2: Run Stage ----------
FROM eclipse-temurin:21-jdk-alpine

# Set working directory in runtime image
WORKDIR /app

# Copy jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
