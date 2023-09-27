# Use an official Tomcat image as the base image
FROM tomcat:9-jre11

# Set the working directory
WORKDIR /usr/local/tomcat/webapps

# Copy your WAR file (your web application) to the container
COPY target/library.war .

# Expose the port your Tomcat server is listening on (default is 8080)
EXPOSE 8082

# Create a directory for external configuration (if it doesn't exist)
RUN mkdir -p /usr/local/tomcat/webapps/library/WEB-INF/classes

# Copy the database properties file to the external configuration directory
COPY target/library/WEB-INF/classes/database.properties /usr/local/tomcat/webapps/library/WEB-INF/classes/

