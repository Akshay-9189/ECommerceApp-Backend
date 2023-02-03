FROM openjdk:17.0

LABEL maintainer="akshay"

ADD target/APIGateway.jar APIGateway.jar

ENTRYPOINT ["java" , "-jar" , "APIGateway.jar"]

EXPOSE 9000