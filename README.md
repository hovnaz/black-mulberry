<h1 align="center">Black Mulberry Project</h1>
<p align="center">
m  <br>
  <i>Black Mulberry is a online web platform for marketplace where users can buy/sell products
    <br> using Java, Spring, Flyway, PostgreSQL, Docker and some other technologies .</i>
  <br>
</p>

<hr>

## Modules
### - Mvc: Run in port 8080
### - Rest: Run in port 8000
### - Core 
### - Data Transfer

## Development Setup
### Prerequisites
- Install [Java] which includes [Maven MavenPackage Manager][mvn]
### Setting Up a Project
Install the Java 11 :

```
https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html
```

Install Maven technology:

```
https://maven.apache.org/download.cgi
```

Install PostgreSQL database:

```
https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
```

Build maven project
```
mvn clean package
```


Mvc Project Run in port 8080
```
java -jar black-mulberry-mvc/.target/${MODULE_VERSION}.jar
```

Rest Project Run in port 8000

```
java -jar black-mulberry-rest/.target/${MMODULE_VERSION}.jar
```

### Docker Run

Docker Compose
```
docker-compose up
```
