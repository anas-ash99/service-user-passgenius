FROM eclipse-temurin:21-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/service-user2-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080
# Set the environment variable for MongoDB connection (change it to your MongoDB service)
ENV SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/pass-genius
ENV JWT_SECRET=f4f8ab275d167fc80b2e4b4ac940a670a4ebeef571296ce5e3b660b139260735
ENV JWT_KEY=jgiorjgAF61lsdfmQHGDF45ewrfwe
# Set the entry point to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]