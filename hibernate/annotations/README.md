[&#8593;](../README.md)

# About
Demonstrate usage of basic hibernate annotations.
For bootstrapping application is used new API available in hibernate version 5.4.9 (StandardServiceRegistry
, MetadataSources, Metadata, SessionFactory)


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