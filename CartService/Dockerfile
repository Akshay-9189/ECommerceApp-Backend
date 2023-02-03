FROM openjdk:17.0

LABEL maintainer="akshay"

ADD target/CartService.jar CartService.jar

ENTRYPOINT ["java" , "-jar" , "CartService.jar"]

EXPOSE 9003