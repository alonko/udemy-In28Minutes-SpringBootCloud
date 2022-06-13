# udemy-In28Minutes-SpringBootCloud
Based on https://www.udemy.com/course/microservices-with-spring-boot-and-spring-cloud

Start the application by running @see com.ak.minutes.rest.webservices.akrestfulwebservices.AkRestfulWebServicesApplication

Swagger UI
===========
http://localhost:8080/swagger-ui/index.html

H2
===
http://localhost:8080/h2-console/
(use "jdbc:h2:mem:testdb" in the JDBC URL field)

Security
=========
After adding spring-boot-starter-security, in order to get results from the API you need 
to add in the Authorization tab "Basic Auth" and use the user "user" and the password from 
the logs (search in the logs for the string "Using generated security password:"). 
Or you can add "spring.security.user.name" and "spring.security.user.password" to application.properties  

Actuator
========
http://localhost:8080/actuator