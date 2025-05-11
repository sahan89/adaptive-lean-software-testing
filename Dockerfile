FROM jenkins/jenkins:lts

# Skip setup wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Add the Groovy script to configure admin user
COPY basic-security.groovy /usr/share/jenkins/ref/init.groovy.d/basic-security.groovy