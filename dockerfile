FROM openjdk:17

WORKDIR /app

COPY target/ms-contacts-0.0.1.jar .


EXPOSE 8080

CMD ["java", "-jar", "ms-contacts-0.0.1.jar"]