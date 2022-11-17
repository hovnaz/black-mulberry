<h1 align="center">Black Mulberry Project</h1>
<p align="center">
<br>
  <i>Black Mulberry is a online web marketplace platform where users can buy/sell products
    <br> using Java, Spring, Flyway, PostgresSQL, Docker and some other technologies .</i>
  <br>
</p>

<hr>

## Modules

- Mvc: Run in port 8080
- Rest: Run in port 8000
- Core
- Data Transfer

## Development Setup instruction

## install devtools

<!-- TOC -->

* [Install ORACLE JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
* [Install Maven package manager](https://maven.apache.org/download.cgi)
* [Install PostgresSQL database](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

<!-- TOC -->

## Setting Up a Project

Setup PostgresSQL

```
username: postgres
password: root
database: black_mulberry
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

## Docker Compose Run

```
docker-compose up
```

## Url website

Rest Module

[http://localhost:8000/api/](http://localhost:8000/api/)

Mvc Module

[http://localhost:8080/](http://localhost:8080/)

