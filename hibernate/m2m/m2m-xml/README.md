[&#8593;](../README.md)

# About
Demonstrate many to many mappng between entities.
In thi example H2 database is used. It is used as in memory database (it comes from
[hibernate.cfg.xml](src/main/resource/hibernate.cfg.xml) from property __hibernate.connection.url__ - jdbc:h2:mem:test.
Needed tables are created during configuration from hibernate.cfg.xml because of property 
__hbm2ddl.auto__ - create-drop. 

# DB datbles

As was mentioned in previous section due to __hbm2ddl.auto__ set to create-drop it isn't necessary to create DB
 tables. But for reference it is here
```roomsql
create table STUDENT (
   id INT NOT NULL auto_increment,
   name VARCHAR(30) default NULL,
   PRIMARY KEY (id)
);

create table SUBJECT (
   id INT NOT NULL auto_increment,
   name VARCHAR(30) default NULL,
   PRIMARY KEY (id)
);

create table STUDENT_SUBJECT (
   student_id INT NOT NULL,
   subject_id INT NOT NULL,
   FOREIGN KEY (student_id) REFERENCES STUDENT(id),
   FOREIGN KEY (subject_id) REFERENCES SUBJECT(id)
);
```

# Source code
Entity __Student__ is persisted together with entity __Subject__. Subjects are modeled as collection which is part of
 Student entity. There exists relation many-to-many. During running of example is after each insertion also written
  content of table STUDENT_SUBJECT.