<h1 align="center">Black Mulberry Project</h1>
<p align="center">
<br>
  <i>Black Mulberry is a online web marketplace platform where users can buy/sell products
    <br> using Java, Spring, Flyway, PostgresSQL, Docker and some other technologies .</i>
  <br>
</p>

<hr>

## Modules

<hr>

- Mvc: Run in port 8080
- Rest: Run in port 8000
- Core
- Data Transfer

## Development Setup instruction

### install devtools

<hr>

<!-- TOC -->

* [Install ORACLE JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
* [Install Maven package manager](https://maven.apache.org/download.cgi)
* [Install PostgresSQL database](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

<!-- TOC -->

## Setting Up a Project

<hr>

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

<hr>

```
docker-compose up
```

## Url website

<hr>

Rest Module

[http://localhost:8000/api/](http://localhost:8000/api/)

Mvc Module

[http://localhost:8080/](http://localhost:8080/)

## Links

<hr>

<!-- TOC -->

* [Trello Project](https://trello.com/w/decodeitspace/)

<!-- TOC -->

## Contributors

<hr>

| Name                                                       | Position                               |
|------------------------------------------------------------|----------------------------------------|
| [Hovhannes Nazaryan](https://github.com/hovnaz)            | Software Engineer                      |
| [Mesrop Mkhitaryan](https://github.com/MesropMkhitaryan)   | Java Backend Engineer                  |
| [Sevak Martirosyan](https://github.com/sevakmartirosyan92) | Java Backend Engineer                  |
| [Karen Gasparyan](https://github.com/mrkaren)              | Delivery & Resource Manager **Mentor** |                                 |
| [Mushegh Ghazaryan](https://github.com/Mushegh128)         | Java Software Engineer **Mentor**      |
