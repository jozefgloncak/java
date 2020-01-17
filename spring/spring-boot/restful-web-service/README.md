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
