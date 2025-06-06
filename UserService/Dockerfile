# ===== Build Stage =====
FROM eclipse-temurin:17-jdk-alpine AS build

# Set work directory
WORKDIR /app

# Copy project files
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# ===== Run Stage =====
FROM eclipse-temurin:17-jdk-alpine

# Set work directory
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/UserService.jar UserService.jar

# Expose port
EXPOSE 9001

# Run the app
ENTRYPOINT ["java", "-jar", "UserService.jar", "application.yml"]