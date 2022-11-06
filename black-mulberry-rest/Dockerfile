FROM openjdk:11-jdk
VOLUME /TMP
EXPOSE 8000
ADD ./target/black-mulberry-rest-0.0.1-SNAPSHOT.jar app-rest.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-Djava.security.egd=file:/dev/./urandom","-jar","app-rest.jar"]
