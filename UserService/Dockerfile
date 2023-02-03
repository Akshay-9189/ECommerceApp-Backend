FROM openjdk:17.0

LABEL maintainer="akshay"

ADD target/UserService.jar UserService.jar

ENTRYPOINT ["java" , "-jar" , "UserService.jar"]

EXPOSE 9001