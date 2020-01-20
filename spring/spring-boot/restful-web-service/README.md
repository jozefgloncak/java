# Security
After adding artifact **spring-boot-starter-security** there is out of the box security support in application.
In log you can find row like this
```
Using generated security password: 35bc1679-94ba-4344-9183-988be337c679
```
It is default password which is necessary to send with every request (in postman, authorization/basic authorization
). Username us **user**. UUID mentioned above is uniquely generated with every new restart of server. Therefore it
 can be prefered to define own credentials as follows.
 
## Own credentials
In [**application.properties**](src/main/resources/application.properties) specify following two lines:
```
spring.security.user.name=usrname
spring.security.user.password=password
```
Then in postman specify this credentials.

# JPA
It is suitable disable (if enabled) security artifact (spring-boot-starter-security). Just comment it out.

It is also suitable to add following properties to application.properties file
```
spring.jpa.show-sql=true
spring.h2.console.enabled=true
```
Now you can look for into like create, drop table in log.

## Insert testing data
In src/main/resources just define file **data.sql** where you can define (inserts) test data. After starting
 application data are automatically populated to DB. To check whether they are there go to h2 console. In browser
  **http://localhost:8080/h2-console/** JDBC URL specify as **jdbc:h2:mem:testdb** it is default value for spring
   boot (no password). 