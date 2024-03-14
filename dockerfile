FROM openjdk:21-jdk
MAINTAINER masonord
ADD build/libs/redDB-1.0-SNAPSHOT.jar app.jar
EXPOSE 6379
ENTRYPOINT ["java", "-jar", "app.jar"]