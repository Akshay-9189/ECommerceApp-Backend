FROM openjdk:17.0

LABEL maintainer="akshay"

ADD target/OrderService.jar OrderService.jar

ENTRYPOINT ["java" , "-jar" , "OrderService.jar"]

EXPOSE 9004