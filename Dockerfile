FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY build/libs/orders-0.0.1-SNAPSHOT.jar orders.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/orders.jar"]