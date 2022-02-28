FROM openjdk:11-jdk-alpine

VOLUME /tmp

ARG JAR_FILE

COPY target/${JAR_FILE} account-service.jar

COPY dockerize dockerize

CMD ./dockerize -wait tcp://book_store-h2-db:8080 -timeout 15m java -Djava.security.egd=file:/dev/./urandom -jar /account-service.jar