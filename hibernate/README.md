# About
Contains example of hibernate based programs. See in simple example that it is only necessary to import dependencies
 _hibernate-core_ (to use hibernate functionality) and _mysql-connector-java_ (to make it possible to connect to
  backend MySQL database). 
  
DB MySQL is demonstrate e. g. in [collections](hibernate-collections/src/main/resource/hibernate.cfg.xml) example.

DB H2 (in memory) is demonstrate e. g. in [many-2-many](many-2-many/src/main/resource/hibernate.cfg.xml) example.

* [basic example](simple/README.md) - contains very simple example with XML mapping (JAVA(entity) - DB(table)) with
 demonstration of CRUD operations. There is only one entity, one DB table
* [collections](hibernate-collections/README.md) - two entities and two DB dables. Demonstrate how collection (map
, set, list...) which contain elements of one entity and is part of second entity can be persisted to DB. Also
 demonstrate relation one-to-many between entities.
* [many 2 many relationship](many-2-many/README.md) - two entities with many2many relationship, 3 DB tables.
* [annotations](annotations/README.md) 


# Various Hibernate aspects in code
* [@CreationTimestamp](annotations/src/test/java/gloncak/jozef/hibernate/annotations/DBTablesTest.java) - see
 isCreationTimestampGeneratedTest()
* [@UpdateTimestamp](annotations/src/test/java/gloncak/jozef/hibernate/annotations/DBTablesTest.java) - see
 isUpdateTimestampGeneratedTest()
* [@Embeddable, @Embedded](embedables/src/test/java/gloncak/jozef/hibernate/embedables/entity/BookTest.java) - see
 isEmbededPropertyPopulated() and corresponding entity class
 ([Book](embedables/src/main/java/gloncak/jozef/hibernate/embedables/entity/Book.java)) in test.