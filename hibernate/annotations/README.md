[&#8593;](../README.md)

# About
Demonstrate usage of basic hibernate annotations.
For bootstrapping application there are used 2 attitudes:
* [new API](src/main/java/gloncak/jozef/hibernate/annotations/App.java) available in hibernate version 5.4.9
 (StandardServiceRegistry
, MetadataSources, Metadata, SessionFactory)
* [JPA API](src/main/java/gloncak/jozef/hibernate/annotations/AppEntityManager.java) __EntityManagerFactory__ which
 provides __EntityManager__ .

There is also example of JUnit testing of DB content through in memmory DB. See test/resources for standalone
 persistence.xml file.

```roomsql
CREATE TABLE VEHICLE(
    "ID" INTEGER NOT NULL,
    "COLOR" VARCHAR(255),
    "VOLUME" INTEGER,
    "PLATE" VARCHAR(255)
)
```

# Source code
Entity __Car__ is persisted to table VEHICLE.

For JPA API is important also META-INF/persistence.xml file which is in resource directory.