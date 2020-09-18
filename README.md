
***ASSESMENT***

<br/>FlightAirServiceApi is the RESTful service Api with embedded apache tomcat as servlet container. It use Java / Spring Boot as Back-End, Jquery, Jsp as Front-End, MongoDB as Database and I designed this project to use and show some of nice Spring Boot features technically which is like CORS Support(uses for Security), Cacheable, TextIndexed(uses for Searching) and Reflection. Application is implemented using Intellij IDEA Environment. FlightAirServiceApi is use Flightstats which is an api that returns information of airports and airlines around the world as JSON. FlightAirService sends request for getting JSON data to the Flightstats Api. It gets all values and save to the MongoDB.

***RUN***

Firstly,you should complete installation of MongoDB before the running my service, afterwards you need to package it with;

```mvn clean package```


If you wanna change default configuration,parameters set in src/main/resources/application.properties you need to give a new properties file with the following parameter;

```java -jar target/assessment-1.0.0-SNAPSHOT.jar --spring.config.location=file:////home/ufuk/my_application.properties```

***Swagger UI***

<br/>By default this assesment will be executed on 8080 port and you'll see the entire endpoints from http://localhost:8080/flightAirService/swagger-ui.html

***IDE***

<br/>For this service we used smart IDE intellij and you can easily start our spring boot application from ```src/main/java/com.ufuk.flightAirServiceApi/FlightAirServiceApplication``` class.

***What You'll See In My Project***

<br/>App is using datas from https://developer.flightstats.com/api-docs/flightstatus/v2 . This api returns pure airports/airlines informations.
What you can do with my FlightAirServiceApi:

* Get active/inactive airports/airlines.

* Get airports/airlines by code.

* Get airports/airlines by City code.

* Get airports/airlines by Iata code.

* Get airports/airlines by Icao code.

* Get airports/airlines by city.

* Get airports/airlines by country name.

* Get airports/airlines by code.

* Get active/inactive airports/airlines by country name.

***Useful Features That Have Been Used***

* ***Spring Boot - CORS Support*** : Cross-Origin Resource Sharing (CORS) is a security concept that allows restricting the resources implemented in web browsers. It prevents the JavaScript code producing or consuming the requests against different origin 

* ***Spring Boot - @cacheable*** :  Caching is a mechanism to enhance the performance of a system. It is a temporary memory that lies between the application and the persistent database. Cache memory stores recently used data items in order to reduce the number of database hits as much as possible.

* ***Search*** : Also you will find how to search items on backendside. ***@TextIndexed*** annotation used. Check searchObjects method.

* ***Reflection*** : You can find Reflection property of Java.You can handle with missing or null equal fields.In this Api,I used " Field declaredField ". It means that returns an array of Field objects reflecting all the fields declared by the class or interface represented by this Class object. This includes public, protected, default (package) access, and private fields, but excludes inherited fields.
