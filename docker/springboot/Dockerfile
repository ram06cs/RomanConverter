FROM openjdk:17-jdk

# working directory and ensure permissions
WORKDIR /usr/src/app
RUN mkdir -p /usr/src/app && \
    chmod -R 755 /usr/src/app

# Argument to specify the JAR file path
ARG JAR_PATH

# Copy the Spring Boot application JAR file into the container workdir
COPY ${JAR_PATH} /usr/src/app/
COPY target/opentelemetry-javaagent.jar /usr/src/app/

# Expose the application port
EXPOSE 8080

# Pass the API version as an environment variable
ENV API_VERSION=${API_VERSION}

# Start the Spring Boot application with OpenTelemetry agent
CMD java -javaagent:/usr/src/app/opentelemetry-javaagent.jar $JVM_OPTIONS -jar /usr/src/app/$JAR_NAME
