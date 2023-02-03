FROM openjdk:17.0

LABEL maintainer="akshay"

ADD target/ProductService.jar ProductService.jar

ENTRYPOINT ["java" , "-jar" , "ProductService.jar"]

EXPOSE 9002