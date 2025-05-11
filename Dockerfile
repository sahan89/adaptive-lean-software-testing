# Base Jenkins image
FROM jenkins/jenkins:lts

# Skip Jenkins setup wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Install OpenJDK 17 for running your Spring Boot app
USER root
RUN apt-get update && apt-get install -y openjdk-17-jdk && apt-get clean

# Set working directory
WORKDIR /opt/app

# Copy Spring Boot JAR file into container
COPY target/adaptive-lean-software-testing-0.0.1-SNAPSHOT.jar adaptive-lean-app.jar

# Copy Groovy script for Jenkins setup
COPY basic-security.groovy /usr/share/jenkins/ref/init.groovy.d/basic-security.groovy

# Expose both Jenkins and Spring Boot app ports
EXPOSE 8080 8081

# Start both Jenkins and your app when the container starts
CMD java -jar adaptive-lean-app.jar & /usr/bin/tini -- /usr/local/bin/jenkins.sh