# About
Contains example of hibernate based programs. See in simple example that it is only necessary to import dependencies
 _hibernate-core_ (to use hibernate functionality) and _mysql-connector-java_ (to make it possible to connect to
  backend MySQL database). 

* [basic example](simple/README.md) - contains very simple example with XML mapping (JAVA(entity) - DB(table)) with
 demonstration of CRUD operations. There is only one entity, one DB table
* [collections](hibernate-collections/README.md) - two entities and two DB dables. Demonstrate how collection (map
, set, list...) which contain elements of one entity and is part of second entity can be persisted to DB. Also
 demonstrate relation one-to-many between entities.

