<h1 align="center">Black Mulberry Rest module</h1>
<p align="center">
m  <br>
  <i>Rest module 
    <br> </i>
  <br>
</p>

<hr>

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

Module Run in port 8000
```
java -jar black-mulberry-rest/.target/${MODULE_VERSION}.jar
```

Use
```
http://localhost:8000/
```