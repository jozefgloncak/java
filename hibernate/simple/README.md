[&#8593;](../README.md)

# About
Demonstrate how can be entity persistet by using hibernate framework.

# Preconditions 
It is also awaited that in backend database already exist table defined via following SQL statement
```roomsql
CREATE TABLE Animal(
   id   INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(20) NOT NULL,
   age  SMALLINT NOT NULL,
   gender TINYINR NOT NULL,
   PRIMARY KEY (ID)
);
```

# Source code
There is entity __Animal__ which has equivalent in backend DB. It is necessary to specify mapping to bakend DB (see
 file Animal.hbm.xml) and also hibernate configuration (see file hibernate.cfg.xml). In configuration file there is
  specified url of backend server, database to connect, username, password and also SQL dialect. Additionally there
   is also list of all mapping files (e. g. Animal.hbm.xml). CRUD operation are done through AnimalManager.