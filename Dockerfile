# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine
# copy application JAR (with libraries inside)
COPY target/order-management-system-1.0.jar /order-management-system-1.0.jar
# specify default command
CMD ["/usr/bin/java", "-jar",  "/order-management-system-1.0.jar"]