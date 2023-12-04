# Weather Application

## Description

The Weather Application gets information about actual weather from external API according to the timetable and saves it to database. 
User can get information about:
1) last saved weather;
2) average weather values during given period.

### Endpoints

1) ```/weather/actual```
   ```get method```
2) ```/weather/average```
   ```post method```

   ```
   Request body
   {
    "from" : "2023-12-03",
    "to" : "2023-12-04"
   }
   ```
Fields 'from' and 'to' are required. 'from' must be before or equal to 'to'.

Returned codes can be:
* 200 - OK
* 204 - if there is no information about weather in database
* 400 - if user sends invalid data (for example, 'from' is after 'to', 'from' or 'to' does not match pattern '2023-12-20',
'from' or/and 'to' is missed, there is no request body)

You can use swagger from http://localhost:8080/swagger-ui/index.html#/ after running application.

### Technologies

* Java 17
* Spring Boot 3
* Spring DATA JPA
* MySQL 8
* JUnit / Mockito
* MapStruct
* Maven
* Lombok
* OpenAPI

## Installation

    properties.location - Preferred location.
    properties.period - Preferred period in milliseconds.
    properties.rapid-key - You need register at https://rapidapi.com/weatherapi/api/weatherapi-com, subscribe and copy your X-RapidAPI-Key at this property.

### Manual Setup

Before running project you should set environment variables in application.yaml

    spring.datasource.url - Database URL. (Example: "jdbc:mysql://localhost:3306/weather_db")
    spring.datasource.username - Database username.
    spring.datasource.password - Database password.

### Docker Setup

Execute the following command to build the project and create the Docker image:

```
docker-compose up
```

