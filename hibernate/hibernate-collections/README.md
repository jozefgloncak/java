[&#8593;](../README.md)

# About
Demonstrate how collections in hibernate are mapped. In example there is couple of entities which are persisted to DB
. There exists relation _one-to-many_ between entitiess.

There is much more possible mapping of [collection types](https://www.tutorialspoint.com/hibernate/hibernate_or_mappings
.htm).  

# Preconditions 
It is also awaited that in backend database already exist table defined via following SQL statement
```roomsql
create table STUDENT (
   id INT NOT NULL auto_increment,
   name VARCHAR(20) default NULL,
   PRIMARY KEY (id)
);

create table SUBJECT (
   id INT NOT NULL auto_increment,
   name VARCHAR(30) default NULL,
   idx INT default NULL, 
   student_id INT default NULL,
   PRIMARY KEY (id)
);
```

# Source code
Entity __Student__ is persisted together with entity __Subject__. Subjects are modeled as collection which is part of
 Student entity. There exists relation one-to-many (one Student can have one or more Subjects). From possible
  implementation of collections has been chosen one where Sujbects are stored as indexed list. For details about hove
   mapping is defined see _Employee.hbm.xml_ file.