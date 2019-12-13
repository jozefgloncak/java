# About
Contains example of hibernate based programs. See in simple example that it is only necessary to import dependencies
 _hibernate-core_ (to use hibernate functionality) and _mysql-connector-java_ (to make it possible to connect to
  backend MySQL database). 
  
DB MySQL is demonstrate e. g. in [collections](hibernate-collections/src/main/resource/hibernate.cfg.xml) example.

DB H2 (in memory) is demonstrate e. g. in [many-2-many](many-2-many/src/main/resource/hibernate.cfg.xml) example.

It is also possible to start h2 server
```shell script
java -cp h2-1.4.200.jar org.h2.tools.Server -ifNoExists
```
then it is possible to connect through h2 console (JDBC  URL: jdbc:h2:tcp://localhost/~/test) and also
 programatically in the same time

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
* [@GeneratedValue](generated-identifiers/src/test/java/gloncak/jozef/hibernate/generated/identifiers/BookTest.java
) - see gradually tests with __BookSequence__, __BookWithIdentifier__, __BookWithTable__
* [@TableGenerator](generated-identifiers/src/main/java/gloncak/jozef/hibernate/generated/identifiers/entity/BookWithTable.java)
\- see also coresponding JUnit test
[isTableGeneratorUsedToGenerate()](generated-identifiers/src/test/java/gloncak/jozef/hibernate/generated/identifiers/BookTest.java)
* [@SequenceGenerator](generated-identifiers/src/main/java/gloncak/jozef/hibernate/generated/identifiers/entity/BookSequence.java)
\- see also coresponding JUnit test [isSequenceUsedToGenerate()](generated-identifiers/src/test/java/gloncak/jozef/hibernate/generated/identifiers/BookTest.java)