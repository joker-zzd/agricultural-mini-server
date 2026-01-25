FROM openjdk:17-jdk-alpine
LABEL author=huangxianjun
COPY target/*.jar /agriculture-mini-server.jar
EXPOSE 8080
ENTRYPOINT ["java","jar","/agriculture-mini-server.jar","&"]