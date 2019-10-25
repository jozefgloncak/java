[&#8593;](../README.md)

# About
In this example transactions it is demonstrated how can be transaction managed. There are 2 possibilities:
* programatically - see [StudentJDBCProgramaticManagementTemplate](src/main/java/gloncak/jozef/springframework/data/access/transaction/impl/template/StudentJDBCProgramaticManagementTemplate.java )
* declarativelly - see [StudentJDBCDeclarativeManagementTemplate](src/main/java/gloncak/jozef/springframework/data/access/transaction/impl/template/StudentJDBCDeclarativeManagementTemplate.java )


. Transaction in example consists of 3 operations which have
 to be together atomic (inserting to one table A, retreiving ID for new created record from table A, creating record
  in table B with foreign key retreived in previous step).

Main program runs in 2 loops. In first programatical principe is demonstrated. In second declarative. For declarative
 version there is also implemented error during creating last data row which cause rollback of whole transaction
  (last record isn't stored to DB).
  
Before program running it is necessary to have created DB tables in backend DBMS
```roomsql
CREATE TABLE Marks(
   SID INT NOT NULL,
   MARKS  INT NOT NULL,
   YEAR   INT NOT NULL
);

CREATE TABLE Student(
   ID   INT NOT NULL,
   NAME VARCHAR(20) NOT NULL,
   AGE  INT NOT NULL,
   PRIMARY KEY (ID)
);
```