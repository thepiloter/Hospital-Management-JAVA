# Multi-stage build for Hospital Management System
FROM maven:3.9.10-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first for better layer caching
COPY pom.xml .

# Download dependencies (cached layer if pom.xml doesn't change)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre

# Create app directory
WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8085
EXPOSE 8085

# Add health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD curl -f http://localhost:8085/patients || exit 1

# Create non-root user for security
RUN groupadd -r hospital && useradd -r -g hospital hospital

# Change ownership of app directory
RUN chown -R hospital:hospital /app

# Switch to non-root user
USER hospital

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]