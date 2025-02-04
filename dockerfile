# Build Java application
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Final image
FROM openjdk:17.0.1-jdk-slim

# Install Python and dependencies
RUN apt-get update && apt-get install -y python3 python3-pip && \
    pip3 install fastapi uvicorn && \
    apt-get clean

# Copy Java application
COPY --from=build /target/api-0.0.1-SNAPSHOT.jar /app/demo.jar

# Copy Python API code
WORKDIR /app
COPY python-api/ /app/python-api/
RUN pip3 install -r /app/python-api/requirements.txt

# Install Supervisor
RUN apt-get install -y supervisor
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# Expose ports
EXPOSE 8080 8000

# Start both applications using Supervisor
CMD ["/usr/bin/supervisord", "-c", "/etc/supervisor/conf.d/supervisord.conf"]
