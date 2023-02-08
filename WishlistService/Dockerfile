FROM openjdk:17.0

LABEL maintainer="akshay"

ADD target/WishListService.jar WishListService.jar

ENTRYPOINT ["java" , "-jar" , "WishListService.jar"]

EXPOSE 9005