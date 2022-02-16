# About
Example demonstrate usage of H2 DB with Spring and JUnit tests.

# Structure
## Test configuration
Configuration of H2 DB is in _test/repository/application.yml_. It is possible to specify __spring.datasource.url__ as:
- _jdbc:h2:mem:{dbName}_ - dbName will be created in memory (In code test class __AppInMemoryDbTest__)
- _jdbc:h2:file:~/test_ - DB with name test will be created in file system in active user directory. Only one 
  connection at the same time
- _jdbc:h2:tcp://localhost/~/test_ - DB with name test will be created. Many connection at the same time. (In code 
  test class __AppTest__)

_spring.jpa.hibernate.ddl-auto_ - define way of of using DDL
- update - update schema (sometimes it isn't possible)
- create-drop - at start create tables, at the end drop tables
- create - at start create tables

_spring.test.database.replace_ - none. Sometimes it is required to inspect DB changes also after test completition. 
This setting is used to protect rollbacking of DB changes after test finish. It has to be used with together with 
annotation _@Rollback(false)_ which has to be used above concrete test.

# Dependency
If it is required to inspect data in H2 DB then it is **very important** to have equal version of h2 dependency in POM 
and local h2 installation for viewing of data.

# Repository for native access
In __EmployerPersonJoinTableRepository__ is example of accessing join table through native SQL (without entity)
  