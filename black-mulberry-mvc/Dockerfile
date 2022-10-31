FROM openjdk:11-jdk
VOLUME /TMP
EXPOSE 8080
ADD ./target/black-mulberry-mvc-0.0.1-SNAPSHOT.jar app-mvc.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-Djava.security.egd=file:/dev/./urandom","-jar","app-mvc.jar"]
