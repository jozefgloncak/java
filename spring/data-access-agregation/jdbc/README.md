[&#8593;](../README.md)

# About
Demonstrate how can be JDBC used with Spring. Demonstrate CRUD operations. Before running it is necessary to insert
 password to _dataSource_ bean
 configuration in _Beans.xml_ file. 
 
It is also awaited that in backend database already exist table defined via following SQL statement
```roomsql
CREATE TABLE Student(
   ID   INT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(20) NOT NULL,
   AGE  INT NOT NULL,
   PRIMARY KEY (ID)
);
```